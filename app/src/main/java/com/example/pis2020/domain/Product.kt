package com.example.pis2020.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(var image: String,
                   var name: String,
                   val quantity: String,
                   val ingredients: String,
                   val nutriments: Nutriments) : Parcelable