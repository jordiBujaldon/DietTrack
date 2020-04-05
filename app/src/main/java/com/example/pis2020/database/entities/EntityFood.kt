package com.example.pis2020.database.entities

import androidx.room.*
import com.example.pis2020.database.converters.ProductConverter
import com.example.pis2020.domain.Product

@Entity(tableName = "food_table")
data class EntityFood(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "status_validation")
    val status: Int,

    @ColumnInfo(name = "product")
    @TypeConverters(ProductConverter::class)
    val product: Product
)