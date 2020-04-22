package com.example.pis2020.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pis2020.databinding.FragmentPuntuacionAlimentoBinding
import com.example.pis2020.domain.Food
import com.example.pis2020.viewmodels.PuntuacionAlimentoViewModel

class PuntuacionAlimentoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPuntuacionAlimentoBinding = FragmentPuntuacionAlimentoBinding
            .inflate(inflater)
        binding.lifecycleOwner = this

        val food: Food = PuntuacionAlimentoFragmentArgs.fromBundle(arguments!!).food
        val viewModel: PuntuacionAlimentoViewModel =
            ViewModelProvider(this, PuntuacionAlimentoViewModel.Factory(food))
            .get(PuntuacionAlimentoViewModel::class.java)

        binding.viewModel = viewModel

        binding.botonAtrasPuntuacionAlimento.setOnClickListener {
            findNavController().navigate(
                PuntuacionAlimentoFragmentDirections
                    .actionPuntuacionAlimentoFragmentToAlimentosFragment(null)
            )
        }

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    PuntuacionAlimentoFragmentDirections
                        .actionPuntuacionAlimentoFragmentToAlimentosFragment(null)
                )
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, callback)

        return binding.root
    }

}