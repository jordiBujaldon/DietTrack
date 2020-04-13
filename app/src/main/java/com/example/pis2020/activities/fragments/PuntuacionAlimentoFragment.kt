package com.example.pis2020.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pis2020.databinding.FragmentPuntuacionAlimentoBinding

class PuntuacionAlimentoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPuntuacionAlimentoBinding
            .inflate(inflater)

        return binding.root
    }

}