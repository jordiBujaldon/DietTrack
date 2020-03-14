package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentEnterBinding

/**
 * A simple [Fragment] subclass.
 */
class EnterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Variable que hace referencia a todas las Views de fragment_enter.xml
        val binding: FragmentEnterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_enter,
            container,
            false
        )

        binding.botonIniciarSesion.setOnClickListener {
            // TODO: Implementar la navegacion del boton
        }

        binding.botonRegistrar.setOnClickListener {
            it.findNavController().navigate(
                EnterFragmentDirections.actionEnterFragmentToRegistrarFragment()
            )
        }

        return binding.root
    }

}
