package com.example.pis2020.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pis2020.database.entities.EntityUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: EntityUser)

    @Query("SELECT * FROM tabla_usuarios WHERE id = :id")
    fun getUser(id: String): EntityUser

}