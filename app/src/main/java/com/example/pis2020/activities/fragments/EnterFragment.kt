package com.example.pis2020.activities.fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentEnterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                it.findNavController().navigate(
                    EnterFragmentDirections.actionEnterFragmentToSigninFragment()
                )
            } else {
                requestCamera()
            }
        }

        binding.botonRegistrar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                it.findNavController().navigate(
                    EnterFragmentDirections.actionEnterFragmentToRegistrarFragment()
                )
            } else {
                requestCamera()
            }
        }

        return binding.root
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
