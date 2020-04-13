package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.activities.MainActivity
import com.example.pis2020.activities.utils.adapters.FoodListAdapter
import com.example.pis2020.databinding.FragmentAlimentosBinding
import com.example.pis2020.viewmodels.AlimentosViewModel
import java.lang.Exception

class AlimentosFragment : Fragment() {

    private val viewModel: AlimentosViewModel by lazy {
        ViewModelProvider(this,
            AlimentosViewModel.Factory(requireActivity().application))
            .get(AlimentosViewModel::class.java)
    }

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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recycleviewFoodlist.adapter = FoodListAdapter(FoodListAdapter.OnClickListener {
            viewModel.navigateToFoodItem(it)
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var barcode: String? = AlimentosFragmentArgs.fromBundle(arguments!!).barcode
        if (barcode != null) {
            try {
                viewModel.saveScannedFood(barcode)
            } catch (e: Exception) {

            }
        }

        viewModel.navigateToCamera.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    AlimentosFragmentDirections.actionAlimentosFragmentToCameraFragment22()
                )
                viewModel.navigateToCameraDone()
            }
        })

        viewModel.navigateToFoodItem.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    AlimentosFragmentDirections.actionAlimentosFragmentToPuntuacionFragment(it)
                )
                viewModel.navigateToFoodItemDone()
            }
        })
        
        super.onActivityCreated(savedInstanceState)
    }
}
