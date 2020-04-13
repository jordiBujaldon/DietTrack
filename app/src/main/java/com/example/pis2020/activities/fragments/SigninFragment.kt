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


    private var signinviewmodel: SignInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    private lateinit var binding:FragmentSigninBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signin, container, false
        )

        binding.botonIniciarSesion2.setOnClickListener {
            it.findNavController().navigate(
                SigninFragmentDirections.actionSigninFragmentToMainFragment()
            )
            signin()

        }

        binding.botonAtrasSignin.setOnClickListener {
            activity?.onBackPressed()
        }



        return binding.root
    }

    fun signin () {

        val contrasena: String = binding.contrasenaLogin.text.toString()
        val email: String = binding.emailLogin.text.toString()


        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(contrasena)){

            signinviewmodel.login(email,contrasena,false)


        }


    }

}
