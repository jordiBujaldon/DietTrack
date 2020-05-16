package com.example.pis2020.network.model

import com.squareup.moshi.Json

data class NetworkProduct(
    @Json(name = "image_url")
    var image: String? = null,

    @Json(name = "product_name")
    var name: String? = null,

    @Json(name = "quantity")
    var quantity: String? = null,

    /*
    @Json(name = "nutriments")
    val nutriments: List<Int>
     */

    @Json(name = "ingredients_text")
    var ingredients: String? = null,

    /*
    @Json(name = "popularity_tags")
    val tags: ??
     */
    @Json(name = "nutriments")
    var nutriments: NetworkNutriments? = null
)
