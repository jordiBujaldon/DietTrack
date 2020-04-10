package com.example.pis2020.domain

import com.example.pis2020.database.entities.EntityFood

fun List<EntityFood>.asDomainModel(): List<Food> {
    return map {
        Food(
            status = it.status,
            product = it.product
        )
    }
}