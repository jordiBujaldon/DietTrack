package com.example.pis2020.activities.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentLlistaEscanerBinding



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LlistaEscanerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LlistaEscanerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LlistaEscanerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLlistaEscanerBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_llista_escaner,
            container,
            false
        )

        return binding.root
    }
}
