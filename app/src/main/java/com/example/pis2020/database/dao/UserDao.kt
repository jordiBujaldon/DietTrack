package com.example.pis2020.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pis2020.database.entities.EntityUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: EntityUser)

    @Update
    fun update(user: EntityUser)

    @Query("SELECT * FROM tabla_usuarios WHERE firebase_id = :id")
    fun getUser(id: String): EntityUser?

}