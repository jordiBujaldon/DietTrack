package com.example.pis2020.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(val status: Int,
                val product: Product) : Parcelable