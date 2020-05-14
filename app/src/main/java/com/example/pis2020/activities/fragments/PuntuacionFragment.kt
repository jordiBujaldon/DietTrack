package com.example.pis2020.activities.fragments


import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.pis2020.databinding.FragmentPuntuacionBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.example.pis2020.viewmodels.PuntuacioViewModel

/**
 * A simple [Fragment] subclass.
 */
class PuntuacionFragment : Fragment() {

    private val viewModel: PuntuacioViewModel by lazy {
        ViewModelProvider(this, PuntuacioViewModel.Factory(requireActivity().application))
            .get(PuntuacioViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPuntuacionBinding = FragmentPuntuacionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.pieChart.visibility = View.GONE
        viewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.user = it
            }

            val pieChart = binding.pieChart

            val nutrients = ArrayList<PieEntry>()

            nutrients.add(PieEntry(it.proteins.toFloat(),"Proteínas"))
            nutrients.add(PieEntry(it.saturedFats.toFloat(),"Grasas sat."))
            nutrients.add(PieEntry(it.fats.toFloat(),"Grasas"))
            nutrients.add(PieEntry(it.sodium.toFloat(),"Sodio"))
            nutrients.add(PieEntry(it.hidratsCarb.toFloat(),"HdC"))
            nutrients.add(PieEntry(it.sugars.toFloat(),"HdC (azucar)"))

            val pieDataSet = PieDataSet(nutrients,"Visitors")
            pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
            pieDataSet.valueTextColor = Color.BLACK
            pieDataSet.valueTextSize = 16f

            val pieData = PieData(pieDataSet)

            pieChart.data = pieData
            pieChart.description.isEnabled = false
            pieChart.centerText = "Nutrientes"
            pieChart.animate()
        })
        binding.mostrarGrafico.setOnClickListener {
            it.visibility = View.GONE
            binding.pieChart.visibility = View.VISIBLE
        }

        return binding.root
    }

}
