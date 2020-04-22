package com.example.pis2020.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nutriments(
    val salt: Double,
    val carbonHydrate: Double,
    val fat: Double,
    val saturatedFat: Double,
    val proteins: Double,
    val energy: Double,
    val sugars: Double,
    val sodium: Double
) : Parcelable