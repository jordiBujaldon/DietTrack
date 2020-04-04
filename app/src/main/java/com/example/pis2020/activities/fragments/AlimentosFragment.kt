package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentAlimentosBinding

class AlimentosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAlimentosBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_alimentos,
            container,
            false
        )

        binding.botonEscaner.setOnClickListener {
            findNavController().navigate(
                AlimentosFragmentDirections.actionAlimentosFragmentToCameraFragment22()
            )
        }

        return binding.root
    }
}
