package com.example.pis2020.activities.fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.pis2020.R
import com.example.pis2020.databinding.FragmentEnterBinding
import com.example.pis2020.databinding.FragmentPuntuacionBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * A simple [Fragment] subclass.
 */
class PuntuacionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPuntuacionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_puntuacion,
            container,
            false
        )

        val pieChart = binding.pieChart

        val nutrients = ArrayList<PieEntry>()

        nutrients.add(PieEntry(400f,"Proteínas"))
        nutrients.add(PieEntry(400f,"Glucidos"))
        nutrients.add(PieEntry(400f,"Sales minerales"))
        nutrients.add(PieEntry(400f,"Agua"))
        nutrients.add(PieEntry(400f,"Lípidos"))
        nutrients.add(PieEntry(400f,"Vitaminas"))

        val pieDataSet = PieDataSet(nutrients,"Visitors")
        pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 16f

        val pieData = PieData(pieDataSet)

        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.centerText = "Nutrientes"
        pieChart.animate()











        return binding.root
    }


}
