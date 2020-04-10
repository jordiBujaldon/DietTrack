package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentSigninBinding

/**
 * A simple [Fragment] subclass.
 */
class SigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSigninBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signin, container, false
        )

        binding.botonIniciarSesion2.setOnClickListener {
            it.findNavController().navigate(
                SigninFragmentDirections.actionSigninFragmentToMainFragment()
            )
        }

        binding.botonAtrasSignin.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

}
