package com.example.pis2020.activities.utils.adapters

import android.app.Application
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pis2020.R
import com.example.pis2020.databinding.FoodSelectionItemBinding
import com.example.pis2020.domain.Food

class SelectionFoodListAdapter(private val application: Application) :
    ListAdapter<Food, SelectionFoodListAdapter.ViewHolder>(DiffCallBack) {

    object DiffCallBack : DiffUtil.ItemCallback<Food>() {

        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: FoodSelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) {
            binding.food = food
            binding.executePendingBindings()
        }

        fun getBinding(): FoodSelectionItemBinding = binding

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodSelectionItemBinding.inflate(
            inflater, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = getItem(position)
        holder.bind(food)
        if (food.isSelected) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.getBinding().root.setBackgroundColor(application.resources.getColor(
                    R.color.colorAccent, null))
                holder.getBinding().nombreAlimento.setTextColor(Color.WHITE)
            }
        } else {
            holder.getBinding().root.setBackgroundColor(Color.WHITE)
            holder.getBinding().nombreAlimento.setTextColor(Color.BLACK)
        }
        holder.itemView.setOnClickListener {
            food.isSelected = !food.isSelected
            if (food.isSelected) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.getBinding().root.setBackgroundColor(application.resources.getColor(
                        R.color.colorAccent, null))
                    holder.getBinding().nombreAlimento.setTextColor(Color.WHITE)
                }
            } else {
                holder.getBinding().root.setBackgroundColor(Color.WHITE)
                holder.getBinding().nombreAlimento.setTextColor(Color.BLACK)
            }
        }
    }
}