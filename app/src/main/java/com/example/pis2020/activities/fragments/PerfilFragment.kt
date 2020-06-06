package com.example.pis2020.activities.fragments


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager

import android.os.Build
import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentPerfilBinding
import com.example.pis2020.viewmodels.PerfilViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.google.firebase.auth.FirebaseAuth

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment() {

    private var imatgePerfil: ImageView? = null

    private val viewModel: PerfilViewModel by lazy {
        ViewModelProvider(this, PerfilViewModel.Factory(requireActivity().application))
            .get(PerfilViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPerfilBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_perfil,
            container,
            false
        )
        imatgePerfil = binding.profileImage
        binding.lifecycleOwner = this
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.user = it
            }
        })

        binding.botonCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            viewModel.navigateToEnterFragment()
        }

        binding.botonCambiarfoto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery()
            } else {
                requestReadExternalPermission()
            }
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.segundaParteLayout.background = context?.getDrawable(R.drawable.profile_layout_shape_background)
            binding.segundaParteLayout.elevation = context?.resources?.getDimension(R.dimen.profile_layout_elevation)!!
        }
        return binding.root
    }

    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST){
            imatgePerfil!!.setImageURI(data!!.data)
            viewModel.updatePhotoUser(data.data.toString())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.navigaetToEnterFragment.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    PerfilFragmentDirections.actionPerfilFragmentToEnterFragment()
                )
                viewModel.navigateToEnterFragmentComplete()
            }
        })
    }

    private fun requestReadExternalPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Permiso necesario")
                .setMessage("Esta aplicación necesita su permiso para poder acceder a" +
                        "las fotos de su dispositivo")
                .setPositiveButton("Aceptar") { _, _ ->
                    ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
        }
    }

    companion object {
        private const val GALLERY_REQUEST = 1000
        private const val STORAGE_PERMISSION_CODE = 2000
    }
    
}
