package com.example.pis2020.activities.fragments


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentSigninBinding
import com.example.pis2020.domain.User
import com.example.pis2020.viewmodels.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.sign

/**
 * A simple [Fragment] subclass.
 */
class SigninFragment : Fragment() {

    private val viewModel: SignInViewModel by lazy {
        ViewModelProvider(this, SignInViewModel.Factory(requireActivity().application))
            .get(SignInViewModel::class.java)
    }
    private lateinit var binding: FragmentSigninBinding
    private val mAuth = FirebaseAuth.getInstance()

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater)

        binding.botonIniciarSesion2.setOnClickListener {
            if (!TextUtils.isEmpty(binding.emailLogin.text.toString()) &&
                !TextUtils.isEmpty(binding.contrasenaLogin.text.toString())){
                // Iniciem sessio amb el correu
                mAuth.signInWithEmailAndPassword(binding.emailLogin.text.toString(),
                    binding.contrasenaLogin.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModel.navigateToMainContent()
                    } else {
                        when (it.exception) {
                            is FirebaseAuthInvalidUserException -> {
                                Snackbar.make(binding.root, "El correo electronico no es correcto",
                                    Snackbar.LENGTH_SHORT).show()
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                Snackbar.make(binding.root, "La contraseña no es correcta",
                                    Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            } else {
                // TODO(Marcar en vermell els camps que no s'han omplert correctament)
            }
        }
        binding.botonRegistrarGoogle.setOnClickListener {
            signIn()
        }
        binding.botonAtrasSignin.setOnClickListener {
            activity?.onBackPressed()
        }

        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // [END config_signin]

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigateToMainContent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                FirebaseFirestore.getInstance().collection("users").document(auth.currentUser!!.email!!).get().addOnSuccessListener {doc ->
                    val user = doc.toObject(User::class.java)
                    viewModel.checkSignedUser(user!!)
                }
                findNavController().navigate(
                    SigninFragmentDirections.actionSigninFragmentToMainFragment()
                )
                viewModel.navigateToMainContentComplete()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    viewModel.navigateToMainContent()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Snackbar.make(binding.root, "Error en la autenticación.", Snackbar.LENGTH_SHORT).show()
                }
            }
    }
    // [END auth_with_google]

    // [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]

    companion object {
        private const val TAG = "RegistrarFragment"
        private const val RC_SIGN_IN = 9001
    }

}
