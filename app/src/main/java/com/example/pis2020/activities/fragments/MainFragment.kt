package com.example.pis2020.activities.fragments


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pis2020.R
import com.example.pis2020.activities.utils.adapters.SelectionFoodListAdapter
import com.example.pis2020.databinding.FragmentMainBinding
import com.example.pis2020.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(requireActivity().application))
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.listaSeleccionAlimentos.adapter = SelectionFoodListAdapter(requireActivity().application)

        binding.botonHecho.setOnClickListener {
            checkList()
            Snackbar.make(binding.root, "Ir a puntuación para ver tus resultados",
                Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }

    private fun checkList() {
        if (viewModel.scannedFoods.value != null) {
            for (i in viewModel.scannedFoods.value!!) {
                if (i.isSelected) {
                    viewModel.addFoodData(i)
                }
                i.isSelected = false
            }
        }
    }

}
