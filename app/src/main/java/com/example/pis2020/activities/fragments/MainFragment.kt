package com.example.pis2020.activities.fragments


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pis2020.R
import com.example.pis2020.activities.utils.adapters.CalendarioAdapter
import com.example.pis2020.activities.utils.getCalendarioDiasSemana
import com.example.pis2020.activities.utils.getCalendarioNumerosMes
import com.example.pis2020.databinding.FragmentMainBinding
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

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

        val calendario = Calendar.getInstance()
        val adapter = CalendarioAdapter()

        binding.calendario.adapter = adapter

        adapter.dataDias = getCalendarioDiasSemana(calendario)
        adapter.dataNumeros = getCalendarioNumerosMes(calendario)

        return binding.root
    }

}
