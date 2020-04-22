package com.example.pis2020.network.model

import com.squareup.moshi.Json

data class NetworkNutriments(
    @Json(name = "salt")
    val salt: Double,

    @Json(name = "carbohydrates")
    val carbonHydrate: Double,

    @Json(name = "fat")
    val fat: Double,

    @Json(name = "saturated-fat")
    val saturatedFat: Double,

    @Json(name = "proteins")
    val proteins: Double,

    @Json(name = "energy")
    val energy: Double,

    @Json(name = "sugars")
    val sugars: Double,

    @Json(name = "sodium")
    val sodium: Double
)