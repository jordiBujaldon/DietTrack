package com.example.pis2020.database.entities

import androidx.room.*

import com.example.pis2020.database.converters.ProductConverter
import com.example.pis2020.domain.Product

@Entity(tableName = "food_table",
        foreignKeys = [ForeignKey(entity = EntityUser::class,
            parentColumns = arrayOf("firebase_id"),
            childColumns = arrayOf("user_id"))
        ],
        indices = [Index(value = arrayOf("user_id"))]
)
data class EntityFood(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "status_validation")
    val status: Int,

    @ColumnInfo(name = "user_id")
    var user_id: String,


    @ColumnInfo(name = "product")
    @TypeConverters(ProductConverter::class)
    val product: Product
)