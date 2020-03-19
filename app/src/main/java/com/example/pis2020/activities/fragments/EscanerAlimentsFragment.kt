package com.example.pis2020.activities.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentEscanerAlimentsBinding


class EscanerAlimentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEscanerAlimentsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_escaner_aliments,
            container,
            false
        )

        return binding.root
    }

}
