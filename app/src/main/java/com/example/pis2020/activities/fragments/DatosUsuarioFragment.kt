package com.example.pis2020.activities.fragments


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
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
import java.text.DateFormat
import java.util.*

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
    private var photo: String? = null

    private val viewModel: DatosUsuarioViewModel by lazy {
        ViewModelProvider(this, DatosUsuarioViewModel.Factory(requireActivity().application))
            .get(DatosUsuarioViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
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
        photo = DatosUsuarioFragmentArgs.fromBundle(requireArguments()).photo

        binding.botonSeleccionFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                binding.inputAge.setText("$dayOfMonth/${month + 1}/$year")
            }, year, month, day).show()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.botonCancelarDatosUsuarios.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.botonContinuarDatosUsuarios.setOnClickListener {
            closeKeyboard()
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
                        weight = binding.inputWeight.text.toString(),
                        photo = photo!!
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
                                    weight = binding.inputWeight.text.toString(),
                                    photo = ""
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

    private fun closeKeyboard() {
        val view: View? = activity?.currentFocus
        view.let {
            val inputManager: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

}
