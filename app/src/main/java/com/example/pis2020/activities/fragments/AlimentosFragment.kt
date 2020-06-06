package com.example.pis2020.activities.fragments


import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.example.pis2020.R
import com.example.pis2020.activities.MainActivity
import com.example.pis2020.activities.utils.adapters.FoodListAdapter
import com.example.pis2020.databinding.FragmentAlimentosBinding
import com.example.pis2020.viewmodels.AlimentosViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.Hold
import java.lang.Exception

class AlimentosFragment : Fragment() {

    private val viewModel: AlimentosViewModel by lazy {
        ViewModelProvider(this,
            AlimentosViewModel.Factory(requireActivity().application))
            .get(AlimentosViewModel::class.java)
    }
    private lateinit var binding: FragmentAlimentosBinding
    private lateinit var adapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlimentosBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        adapter = FoodListAdapter(FoodListAdapter.OnClickListener { food ->
            viewModel.navigateToFoodItem(food)
        })
        binding.recycleviewFoodlist.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var barcode: String? = AlimentosFragmentArgs.fromBundle(requireArguments()).barcode
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

        viewModel.showSnackbar.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(binding.root, "Producto no encontrado", Snackbar.LENGTH_LONG).show()
            }
        })
        
        super.onActivityCreated(savedInstanceState)
    }
}
