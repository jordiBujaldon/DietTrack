package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentDatosUsuarioBinding

/**
 * A simple [Fragment] subclass.
 */
class DatosUsuarioFragment : Fragment() {

    private lateinit var binding: FragmentDatosUsuarioBinding
    //Prova
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

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.botonCancelarDatosUsuarios.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.botonContinuarDatosUsuarios.setOnClickListener {
            findNavController().navigate(
                DatosUsuarioFragmentDirections.actionDatosUsuarioFragmentToMainFragment()
            )
        }
    }
}
