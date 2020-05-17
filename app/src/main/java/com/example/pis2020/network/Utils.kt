package com.example.pis2020.network

import com.example.pis2020.database.entities.EntityFood
import com.example.pis2020.domain.Nutriments
import com.example.pis2020.domain.Product
import com.example.pis2020.network.model.NetworkFood
import com.example.pis2020.network.model.NetworkNutriments
import com.example.pis2020.network.model.NetworkProduct

fun NetworkNutriments.asDomainModel(): Nutriments {
    return Nutriments(
        salt = salt!!,
        carbonHydrate = carbonHydrate!!,
        fat = fat!!,
        saturatedFat = saturatedFat!!,
        proteins = proteins!!,
        energy = energy!!,
        sugars = sugars!!,
        sodium = sodium!!
    )
}

fun NetworkProduct.asDomainModel(): Product {
    return Product(
        image = image!!,
        name = name!!,
        quantity = quantity,
        ingredients = ingredients,
        nutriments = nutriments!!.asDomainModel()
    )
}

fun NetworkFood.asDatabaseModel(id: String): EntityFood {
    return EntityFood(
        user_id = id,
        status = status,
        product = product!!.asDomainModel()
    )
}