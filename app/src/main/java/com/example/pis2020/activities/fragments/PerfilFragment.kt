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
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentPerfilBinding
import com.example.pis2020.ia.camera.CameraSource
import com.example.pis2020.viewmodels.PerfilViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.xml.transform.OutputKeys.VERSION

//import com.example.pis2020.databinding.FragmentPerfilB

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


        binding.botonCambiarfoto.setOnClickListener(){

            pickImageFromGallery()
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
        startActivityForResult(intent, 1000)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1000){

            imatgePerfil!!.setImageURI(data!!.data)

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
    
}
