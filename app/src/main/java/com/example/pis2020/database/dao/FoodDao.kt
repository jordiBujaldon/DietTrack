package com.example.pis2020.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.pis2020.database.entities.EntityFood

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: EntityFood)

    @Query("SELECT * FROM food_table")
    fun getFoodList(): LiveData<List<EntityFood>>

}