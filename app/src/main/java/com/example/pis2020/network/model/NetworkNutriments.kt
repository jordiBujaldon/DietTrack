package com.example.pis2020.network.model

import com.squareup.moshi.Json

data class NetworkNutriments(
    @Json(name = "salt")
    val salt: Double? = null,

    @Json(name = "carbohydrates")
    val carbonHydrate: Double? = null,

    @Json(name = "fat")
    val fat: Double? = null,

    @Json(name = "saturated-fat")
    val saturatedFat: Double? = null,

    @Json(name = "proteins")
    val proteins: Double? = null,

    @Json(name = "energy")
    val energy: Double? = null,

    @Json(name = "sugars")
    val sugars: Double? = null,

    @Json(name = "sodium")
    val sodium: Double? = null
)