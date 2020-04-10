package com.example.pis2020.database

import android.content.Context
import androidx.room.*
import com.example.pis2020.database.converters.ProductConverter
import com.example.pis2020.database.dao.FoodDao
import com.example.pis2020.database.entities.EntityFood

@Database(entities = [EntityFood::class], version = 1, exportSchema = false)
@TypeConverters(ProductConverter::class)
abstract class DietTrackDatabase : RoomDatabase() {

    abstract val foodDao: FoodDao

    companion object {
        @Volatile
        private var INSTANCE: DietTrackDatabase? = null

        fun getInstance(context: Context): DietTrackDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        DietTrackDatabase::class.java,
                        "diettrack").fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }

}