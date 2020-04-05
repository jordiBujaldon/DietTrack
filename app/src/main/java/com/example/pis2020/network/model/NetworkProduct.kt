package com.example.pis2020.network.model

import com.squareup.moshi.Json

data class NetworkProduct(
    @Json(name = "image_url")
    val image: String,

    @Json(name = "product_name")
    val name: String
)
