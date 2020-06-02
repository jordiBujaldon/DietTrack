package com.example.pis2020.database.entities

import android.net.Uri
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
    val age: String,

    @ColumnInfo(name = "altura")
    val height: Double,

    @ColumnInfo(name = "peso")
    val weight: Double,

    @ColumnInfo(name = "photo")
    val photo: String,

    @ColumnInfo(name = "total_calories")
    val calories: Double,

    @ColumnInfo(name = "total_hidr_carb")
    val hidratsCarb: Double,

    @ColumnInfo(name = "total_proteins")
    val proteins: Double,

    @ColumnInfo(name = "total_sugars")
    val sugars: Double,

    @ColumnInfo(name = "total_fats")
    val fats: Double,

    @ColumnInfo(name = "total_satured_fats")
    val saturedFats: Double,

    @ColumnInfo(name = "total_sodium")
    val sodium: Double
)