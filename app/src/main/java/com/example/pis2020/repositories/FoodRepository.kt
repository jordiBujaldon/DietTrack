package com.example.pis2020.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import com.example.pis2020.database.DietTrackDatabase
import com.example.pis2020.database.dao.FoodDao
import com.example.pis2020.database.entities.EntityFood
import com.example.pis2020.domain.Food
import com.example.pis2020.domain.asDomainModel
import com.example.pis2020.network.OpenFoodApi
import com.example.pis2020.network.asDatabaseModel
import com.example.pis2020.network.model.NetworkFood

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodRepository(val application: Application) {

    private val foodDao: FoodDao = DietTrackDatabase.getInstance(application).foodDao
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val id: String? = FirebaseAuth.getInstance().uid

    val foodList: LiveData<List<Food>> =
        Transformations.map(foodDao.getFoodListFromUserWithId(id!!)) { list ->
            list.asDomainModel()
    }

    suspend fun saveFoodWithBarcode(barcode: String) {
        withContext(Dispatchers.IO) {
            val networkFood: NetworkFood = OpenFoodApi.retorfitService.getFood(barcode).await()
            val entityFood: EntityFood = networkFood.asDatabaseModel(id!!)
            db.collection("food-list").document(barcode).set(entityFood)
            foodDao.insert(entityFood)
        }
    }

}