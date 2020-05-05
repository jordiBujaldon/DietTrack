package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentPuntuacionBinding
import com.example.pis2020.viewmodels.PuntuacioViewModel

/**
 * A simple [Fragment] subclass.
 */
class PuntuacionFragment : Fragment() {

    private val viewModel: PuntuacioViewModel by lazy {
        ViewModelProvider(this, PuntuacioViewModel.Factory(requireActivity().application))
            .get(PuntuacioViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPuntuacionBinding = FragmentPuntuacionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.user = it
            }
        })

        return binding.root
    }

}
