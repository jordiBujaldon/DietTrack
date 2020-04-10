package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.pis2020.R
import com.example.pis2020.activities.MainActivity
import com.example.pis2020.activities.utils.adapters.FoodListAdapter
import com.example.pis2020.databinding.FragmentAlimentosBinding
import com.example.pis2020.viewmodels.AlimentosViewModel

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
            // TODO
        })

        binding.botonEscaner.setOnClickListener {
            findNavController().navigate(
                AlimentosFragmentDirections.actionAlimentosFragmentToCameraFragment22()
            )
        }

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // TODO
                }
            }
        activity?.onBackPressedDispatcher?.addCallback(this, callback)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var barcode: String? = AlimentosFragmentArgs.fromBundle(arguments!!).barcode
        if (barcode != null) {
            viewModel.saveScannedFood(barcode)
        }
        super.onActivityCreated(savedInstanceState)
    }
}
