package com.example.pis2020.domain

import com.example.pis2020.database.entities.EntityFood
import com.example.pis2020.database.entities.EntityUser
import com.google.firebase.auth.FirebaseUser

fun List<EntityFood>.asDomainModel(): List<Food> {
    return map {
        Food(
            status = it.status,
            userId = it.user_id,
            product = it.product
        )
    }
}

fun FirebaseUser.asDomainModel(): User {
    return User(
        id = uid,
        email = email!!,
        password = "",
        username = "",
        age = 0,
        height = 0.0,
        weight = 0.0
    )
}

fun User.asDatabaseModel(): EntityUser {
    return EntityUser(
        id = id!!,
        email = email!!,
        password = password!!,
        username = username!!,
        age = age!!,
        height = height!!,
        weight = weight!!
    )
}