package com.example.pis2020.domain

data class User(
    val id: String? = null,

    val email: String? = null,

    val password: String? = null,

    val username: String? = null,

    val age: Int? = null,

    val height: Double? = null,

    val weight: Double? = null,

    val calories: Double? = null,

    var hidratsCarb: Double = 0.0,

    var proteins: Double = 0.0,

    var sugars: Double = 0.0,

    var fats: Double = 0.0,

    var saturedFats: Double = 0.0,

    var sodium: Double = 0.0
)