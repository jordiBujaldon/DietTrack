package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

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

        return binding.root
    }
}
