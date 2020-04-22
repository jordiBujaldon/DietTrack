package com.example.pis2020.activities.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pis2020.databinding.ItemDietListBinding
import com.example.pis2020.domain.Diet

class DietListAdapter : ListAdapter<Diet, DietListAdapter.ViewHolder>(DiffUtilObject) {

    object DiffUtilObject : DiffUtil.ItemCallback<Diet>() {

        override fun areItemsTheSame(oldItem: Diet, newItem: Diet): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Diet, newItem: Diet): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(val binding: ItemDietListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(diet: Diet) {
            binding.diet = diet
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDietListBinding = ItemDietListBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {

        }
        holder.bind(getItem(position))
    }

}