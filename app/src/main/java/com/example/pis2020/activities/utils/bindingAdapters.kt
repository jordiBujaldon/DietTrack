package com.example.pis2020.activities.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView

import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.pis2020.activities.utils.adapters.DietListAdapter

import com.example.pis2020.activities.utils.adapters.FoodListAdapter
import com.example.pis2020.activities.utils.adapters.SelectionFoodListAdapter
import com.example.pis2020.domain.Diet
import com.example.pis2020.domain.Food
import java.text.DecimalFormat

@BindingAdapter("recycleListFood")
fun RecyclerView.setRecycleListFood(list: List<Food>?) {
    val adapter: FoodListAdapter = adapter as FoodListAdapter
    adapter.submitList(list)
}

@BindingAdapter("recycleListDiets")
fun RecyclerView.setRecycleListDiet(list: List<Diet>?) {
    val adapter: DietListAdapter = adapter as DietListAdapter
    adapter.submitList(list)
}

@BindingAdapter("recycleSelectFood")
fun RecyclerView.setRecycleSelectFood(list: List<Food>?) {
    val adapter: SelectionFoodListAdapter = adapter as SelectionFoodListAdapter
    adapter.submitList(list)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setNutriment")
fun TextView.setTextViewDouble(value: Double?) {
    value.let {
        val form = DecimalFormat("#.##")
        this.text = "${form.format(value)} g"
    }
}

@BindingAdapter("setText")
fun TextView.setTextView(newText: String?) {
    newText?.let {
        this.text = newText
    }
}

@BindingAdapter("doubleNumber")
fun TextView.setDoubleNumber(value: Double?) {
    value?.let {
        val form = DecimalFormat("#.##")
        this.text = form.format(value)
    }
}

@BindingAdapter("setImage")
fun ImageView.setImageView(url: String?) {
    if (url != null) {
        // Convertim la imatge de URL a URI
        // Ens assegurem que l'URL sigui de tipus HTTPS ja que el servidor Back-End
        // d'on estem agafant les dades es de tipus HTTPS
        val imgUri = url.toUri().buildUpon().scheme("https").build()

        // Convertim la imatge URI a ImageView
        Glide.with(context)
            .load(imgUri)
            .into(this)
    } else {

    }
}