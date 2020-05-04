package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentPerfilBinding
import com.example.pis2020.viewmodels.PerfilViewModel

//import com.example.pis2020.databinding.FragmentPerfilB

/**
 * A simple [Fragment] subclass.
 */
class PerfilFragment : Fragment() {

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
        binding.lifecycleOwner = this
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.user = it
            }
        })

        return binding.root
    }
    
}
