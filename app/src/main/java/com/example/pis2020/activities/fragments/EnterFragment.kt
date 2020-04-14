package com.example.pis2020.activities.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentEnterBinding
import com.example.pis2020.viewmodels.EnterViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * A simple [Fragment] subclass.
 */
class EnterFragment : Fragment() {

    private val viewModel: EnterViewModel by lazy {
        ViewModelProvider(this, EnterViewModel.Factory(requireActivity().application))
            .get(EnterViewModel::class.java)
    }

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
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                viewModel.navigateToSignin()
            } else {
                requestCamera()
            }
        }

        binding.botonRegistrar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                viewModel.navigateToSignup()
            } else {
                requestCamera()
            }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Naveguem a la pantalla principal de l'app si l'usuari ja esta guardat
        // a l'aplicacio
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                findNavController().navigate(
                    EnterFragmentDirections.actionEnterFragmentToMainFragment()
                )
            }
        })

        // Naveguem a la pantalla d'iniciar sessio
        viewModel.navigateToSignin.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    EnterFragmentDirections.actionEnterFragmentToSigninFragment()
                )
                viewModel.navigateToSigininComplete()
            }
        })

        // Naveguem a la pantalla de registrar-se
        viewModel.navigateToSignup.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    EnterFragmentDirections.actionEnterFragmentToRegistrarFragment()
                )
                viewModel.navigateToSignupComplete()
            }
        })
    }

    private fun requestCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                Manifest.permission.CAMERA)) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Permiso necesario")
                .setMessage("Esta aplicación necesita su permiso para poder activar" +
                        " la camara para escanear los productos")
                .setPositiveButton("Aceptar") { _, _ ->
                    ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(Manifest.permission.CAMERA), 1)
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CAMERA), 1)
        }
    }

}
