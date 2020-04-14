package com.example.pis2020.domain

data class User(
    val id: String,
    val email: String,
    var password: String,
    var username: String,
    var age: Int,
    var height: Double,
    var weight: Double
)