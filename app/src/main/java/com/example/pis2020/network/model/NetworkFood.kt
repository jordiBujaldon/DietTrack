package com.example.pis2020.network.model

import com.squareup.moshi.Json

data class NetworkFood(
    @Json(name = "status")
    val status: Int,

    @Json(name = "product")
    val product: NetworkProduct
)