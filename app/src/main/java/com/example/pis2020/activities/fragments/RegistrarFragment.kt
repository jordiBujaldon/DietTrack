package com.example.pis2020.activities.fragments


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentRegistrarBinding
import com.example.pis2020.viewmodels.RegistrarViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegistrarFragment : Fragment() {

    private lateinit var binding: FragmentRegistrarBinding

    private val viewModel: RegistrarViewModel by lazy {
        ViewModelProvider(this).get(RegistrarViewModel::class.java)
    }

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
            if (!TextUtils.isEmpty(binding.inputNombreUsuario.text.toString()) &&
                !TextUtils.isEmpty(binding.inputContrasena.text.toString()) &&
                !TextUtils.isEmpty(binding.inputCorreoElectronico.text.toString())){
                viewModel.naviagetToUserData()
            } else {
                // TODO(Marcar en vermell els camps que no s'han omplert correctament)
            }
            // createNewAccount()
        }

        binding.botonRegistrarGoogle.setOnClickListener {
            // TODO
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Naveguem a la pantalla de les dades dels usuaris
        viewModel.navigateToUserData.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    RegistrarFragmentDirections.actionRegistrarFragmentToDatosUsuarioFragment(
                        email = binding.inputCorreoElectronico.text.toString(),
                        password = binding.inputContrasena.text.toString(),
                        username = binding.inputNombreUsuario.text.toString()
                    )
                )
                viewModel.navigateToUserDataComplete()
            }
        })
    }

}

