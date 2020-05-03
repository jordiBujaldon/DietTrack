package com.example.pis2020.activities.fragments


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentSigninBinding
import com.example.pis2020.viewmodels.SignInViewModel
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

        binding.botonAtrasSignin.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigateToMainContent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    SigninFragmentDirections.actionSigninFragmentToMainFragment()
                )
                viewModel.navigateToMainContentComplete()
            }
        })
    }

    private fun checkUser(email: String) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("users").document(email).get().addOnSuccessListener { user ->
            val user = user.toObject(User::class.java)
            if (user != null) {
                viewModel.checkSignedUser(user)
            }
        }
    }

}
