package com.example.pis2020.activities.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pis2020.activities.utils.adapters.FoodListAdapter
import com.example.pis2020.domain.Food

@BindingAdapter("recycleList")
fun RecyclerView.setRecycleList(list: List<Food>?) {
    val adapter: FoodListAdapter = adapter as FoodListAdapter
    adapter.submitList(list)
}

@BindingAdapter("setText")
fun TextView.setTextView(newText: String?) {
    newText?.let {
        this.text = newText
    }
}

@BindingAdapter("setImage")
fun ImageView.setImageView(url: String?) {
    url?.let {
        // Convertim la imatge de URL a URI
        // Ens assegurem que l'URL sigui de tipus HTTPS ja que el servidor Back-End
        // d'on estem agafant les dades es de tipus HTTPS
        val imgUri = it.toUri().buildUpon().scheme("https").build()

        // Convertim la imatge URI a ImageView
        Glide.with(context)
            .load(imgUri)
            .into(this)
    }
}