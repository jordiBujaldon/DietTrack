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
        age = "",
        height = 0.0,
        weight = 0.0,
        calories = 0.0,
        hidratsCarb = 0.0,
        proteins = 0.0,
        sugars = 0.0,
        fats = 0.0,
        saturedFats = 0.0,
        sodium = 0.0
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
        weight = weight!!,
        calories = calories!!,
        hidratsCarb = hidratsCarb!!,
        proteins = proteins!!,
        sugars = sugars!!,
        fats = fats!!,
        saturedFats = saturedFats!!,
        sodium = sodium!!
    )
}

fun EntityUser.asDomainModel(): User {
    return User(
        id = id,
        email = email,
        password = password,
        username = username,
        age = age,
        height = height,
        weight = weight,
        calories = calories,
        hidratsCarb = hidratsCarb,
        proteins = proteins,
        sugars = sugars,
        fats = fats,
        saturedFats = saturedFats,
        sodium = sodium
    )
}