package com.example.pis2020.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val image: String,
                   val name: String) : Parcelable