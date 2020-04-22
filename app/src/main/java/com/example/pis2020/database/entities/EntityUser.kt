package com.example.pis2020.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuarios")
data class EntityUser(
    @PrimaryKey
    @ColumnInfo(name = "firebase_id")
    val id: String,

    @ColumnInfo(name = "correo")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "nombre_usuario")
    val username: String,

    @ColumnInfo(name = "edad")
    val age: Int,

    @ColumnInfo(name = "altura")
    val height: Double,

    @ColumnInfo(name = "peso")
    val weight: Double
)