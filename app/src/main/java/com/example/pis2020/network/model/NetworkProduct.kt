package com.example.pis2020.network.model

import com.squareup.moshi.Json

data class NetworkProduct(
    @Json(name = "image_url")
    val image: String,

    @Json(name = "product_name")
    val name: String,

    @Json(name = "quantity")
    val quantity: String,

    /*
    @Json(name = "nutriments")
    val nutriments: List<Int>
     */

    @Json(name = "ingredients_text")
    val ingredients: String,

    /*
    @Json(name = "popularity_tags")
    val tags: ??
     */
    @Json(name = "nutriments")
    val nutriments: NetworkNutriments
)
