package com.example.pis2020.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.R
import com.example.pis2020.activities.utils.adapters.DietListAdapter
import com.example.pis2020.databinding.FragmentDietasBinding
import com.example.pis2020.viewmodels.DietListViewModel

class DietListFragment : Fragment() {

    private val viewModel: DietListViewModel by lazy {
        ViewModelProvider(this, DietListViewModel.Factory(requireActivity().application))
            .get(DietListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDietasBinding = FragmentDietasBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.dietList.adapter = DietListAdapter()

        return binding.root
    }


}