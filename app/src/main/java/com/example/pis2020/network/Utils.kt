package com.example.pis2020.network

import com.example.pis2020.database.entities.EntityFood
import com.example.pis2020.domain.Product
import com.example.pis2020.network.model.NetworkFood
import com.example.pis2020.network.model.NetworkProduct

fun NetworkProduct.asDomainModel(): Product {
    return Product(
        image = image,
        name = name
    )
}

fun NetworkFood.asDatabaseModel(): EntityFood {
    return EntityFood(
        status = status,
        product = product.asDomainModel()
    )
}