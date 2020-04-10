package com.example.pis2020.database.converters

import androidx.room.TypeConverter
import com.example.pis2020.domain.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductConverter {

    /**
     *
     */
    @TypeConverter
    fun convertToJson(product: Product): String {
        return Gson().toJson(product)
    }

    /**
     *
     */
    @TypeConverter
    fun convertToProduct(jsonString: String): Product {
        val type = object : TypeToken<Product>() {}.type
        return Gson().fromJson<Product>(jsonString, type)
    }

}
