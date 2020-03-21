package com.example.pis2020.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentDietasBinding

class DietListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDietasBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dietas,
            container,
            false
        )
        return binding.root
    }


}