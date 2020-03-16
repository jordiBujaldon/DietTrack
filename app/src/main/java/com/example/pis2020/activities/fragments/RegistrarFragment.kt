package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentRegistrarBinding

/**
 * A simple [Fragment] subclass.
 */
class RegistrarFragment : Fragment() {

    private lateinit var binding: FragmentRegistrarBinding

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
        }

        binding.botonRegistrarGoogle.setOnClickListener {
            // TODO
        }
    }
}
