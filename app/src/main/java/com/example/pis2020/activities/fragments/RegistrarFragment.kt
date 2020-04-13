package com.example.pis2020.activities.fragments


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentRegistrarBinding
import com.example.pis2020.viewmodels.RegistrarViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class RegistrarFragment : Fragment() {

    private lateinit var binding: FragmentRegistrarBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var viewmodelUsuari:RegistrarViewModel

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


        viewmodelUsuari = ViewModelProvider(this).get(RegistrarViewModel::class.java)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.botonAtrasRegistrarse.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.botonRegistrar.setOnClickListener {
            it.findNavController().navigate(
                RegistrarFragmentDirections.actionRegistrarFragmentToDatosUsuarioFragment()
            )
            createNewAccount()

        }

        binding.botonRegistrarGoogle.setOnClickListener {
            // TODO
        }
    }

    fun register(view: View) {

            createNewAccount()

    }
    //Repositori
    private fun createNewAccount() {

        val usuario: String = binding.inputNombreUsuario.text.toString()
        val contrasena: String = binding.inputContrasena.text.toString()
        val email: String = binding.inputCorreoElectronico.text.toString()

        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(contrasena) && !TextUtils.isEmpty(email)){

            viewmodelUsuari.registrar(email,contrasena,false)


        }
    }


    //Repositori
    private fun verifyEmail(user:FirebaseUser){

        user.sendEmailVerification().addOnCompleteListener(){
            task ->

            if(task.isComplete){
                Toast.makeText(context,"Email enviado", Toast.LENGTH_LONG).show()

            }

        }

    }
}

