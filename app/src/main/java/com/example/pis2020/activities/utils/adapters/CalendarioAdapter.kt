package com.example.pis2020.activities.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pis2020.R

class CalendarioAdapter : RecyclerView.Adapter<CalendarioAdapter.ViewHolder>() {

    var dataNumeros = listOf<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var dataDias = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_calendario, parent, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataNumeros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val numero = dataNumeros[position]
        val dia = dataDias[position]

        holder.numeroMes.text = numero.toString()
        holder.diaSemana.text = dia
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val numeroMes: TextView = view.findViewById(R.id.numero_mes)
        val diaSemana: TextView = view.findViewById(R.id.dia_semana)
    }

}