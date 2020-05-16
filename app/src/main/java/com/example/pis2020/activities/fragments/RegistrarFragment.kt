package com.example.pis2020.activities.fragments


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentRegistrarBinding
import com.example.pis2020.viewmodels.RegistrarViewModel

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * A simple [Fragment] subclass.
 */
class RegistrarFragment : Fragment() {

    private lateinit var binding: FragmentRegistrarBinding

    private val viewModel: RegistrarViewModel by lazy {
        ViewModelProvider(this).get(RegistrarViewModel::class.java)
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registrar,
            container,
            false
        )

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
        // [END initialize_auth]

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.botonAtrasRegistrarse.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.botonRegistrar.setOnClickListener {
            if (!TextUtils.isEmpty(binding.inputNombreUsuario.text.toString()) &&
                !TextUtils.isEmpty(binding.inputContrasena.text.toString()) &&
                !TextUtils.isEmpty(binding.inputCorreoElectronico.text.toString())){
                viewModel.naviagetToUserDataEmail()
            }
        }

        binding.botonRegistrarGoogle.setOnClickListener {
            signIn()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Naveguem a la pantalla de les dades dels usuaris
        viewModel.navigateToUserDataEmail.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    RegistrarFragmentDirections.actionRegistrarFragmentToDatosUsuarioFragment(
                        email = binding.inputCorreoElectronico.text.toString(),
                        password = binding.inputContrasena.text.toString(),
                        username = binding.inputNombreUsuario.text.toString(),
                        google = false,
                        uid = ""
                    )
                )
                viewModel.navigateToUserDataEmailComplete()
            }
        })
        viewModel.navigateToUserDataGoogle.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    RegistrarFragmentDirections.actionRegistrarFragmentToDatosUsuarioFragment(
                        email = it.email!!,
                        password = "",
                        username = it.displayName!!,
                        google = true,
                        uid = it.uid
                    )
                )
                viewModel.navigateToUserDataGoogleComplete()
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
                    val user = auth.currentUser
                    viewModel.navigateToUserDataGoogle(user!!)
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

