package com.example.pis2020.activities.fragments


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentDatosUsuarioBinding
import com.example.pis2020.viewmodels.DatosUsuarioViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

/**
 * A simple [Fragment] subclass.
 */
class DatosUsuarioFragment : Fragment() {

    private lateinit var binding: FragmentDatosUsuarioBinding
    private var username: String? = null
    private var email: String? = null
    private var password: String? = null
    private var id: String? = null
    private var google: Boolean = false

    private val viewModel: DatosUsuarioViewModel by lazy {
        ViewModelProvider(this, DatosUsuarioViewModel.Factory(requireActivity().application))
            .get(DatosUsuarioViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_datos_usuario,
            container,
            false
        )
        binding.viewModel =  viewModel

        username = DatosUsuarioFragmentArgs.fromBundle(requireArguments()).username
        email = DatosUsuarioFragmentArgs.fromBundle(requireArguments()).email
        password = DatosUsuarioFragmentArgs.fromBundle(requireArguments()).password
        google = DatosUsuarioFragmentArgs.fromBundle(requireArguments()).google
        id = DatosUsuarioFragmentArgs.fromBundle(requireArguments()).uid

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.botonCancelarDatosUsuarios.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.botonContinuarDatosUsuarios.setOnClickListener {
            if (!TextUtils.isEmpty(binding.inputAge.text.toString()) &&
                !TextUtils.isEmpty(binding.inputHeight.text.toString()) &&
                !TextUtils.isEmpty(binding.inputWeight.text.toString())){
                if (google) {
                    viewModel.createAccount(
                        id = id!!,
                        email = email!!,
                        password = password!!,
                        username = username!!,
                        age = binding.inputAge.text.toString(),
                        height = binding.inputHeight.text.toString(),
                        weight = binding.inputWeight.text.toString()
                    )
                    // Naveguem a la part principal de l'aplicacio
                    findNavController().navigate(
                        DatosUsuarioFragmentDirections.actionDatosUsuarioFragmentToMainFragment()
                    )
                } else {
                    // Creem el nou compte de DietTrack
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, password!!)
                        .addOnCompleteListener(requireActivity()) {
                            if (it.isSuccessful) {
                                // Creem un nou compte i el guardem a la base de dades local i tambe
                                // a Firebase
                                viewModel.createAccount(
                                    id = it.result?.user!!.uid,
                                    email = email!!,
                                    password = password!!,
                                    username = username!!,
                                    age = binding.inputAge.text.toString(),
                                    height = binding.inputHeight.text.toString(),
                                    weight = binding.inputWeight.text.toString()
                                )
                                // Naveguem a la part principal de l'aplicacio
                                findNavController().navigate(
                                    DatosUsuarioFragmentDirections.actionDatosUsuarioFragmentToMainFragment()
                                )
                            } else {
                                when (it.exception) {
                                    is FirebaseAuthInvalidCredentialsException -> {
                                        Snackbar.make(binding.root, "Correo electronico no valido",
                                            Snackbar.LENGTH_SHORT).show()
                                    }
                                    is FirebaseAuthUserCollisionException -> {
                                        Snackbar.make(binding.root, "Este usuario ya existe",
                                            Snackbar.LENGTH_SHORT).show()
                                    }
                                    is FirebaseAuthWeakPasswordException -> {
                                        Snackbar.make(binding.root, "Contraseña no valida, introduce otra",
                                            Snackbar.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                }
            } else {
                // TODO(Marcar en vermell els camps que no s'han omplert correctament)
            }
        }
    }

}
