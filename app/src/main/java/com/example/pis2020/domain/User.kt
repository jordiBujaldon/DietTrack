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

    val hidratsCarb: Double? = null,

    val proteins: Double? = null,

    val sugars: Double? = null,

    val fats: Double? = null,

    val saturedFats: Double? = null,

    val sodium: Double? = null
)