package com.example.pis2020.repositories

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.Transformations
import com.example.pis2020.database.DietTrackDatabase
import com.example.pis2020.database.entities.EntityFood
import com.example.pis2020.domain.Food
import com.example.pis2020.domain.asDomainModel
import com.example.pis2020.network.OpenFoodApi
import com.example.pis2020.network.asDatabaseModel
import com.example.pis2020.network.asDomainModel
import com.example.pis2020.network.model.NetworkFood
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodRepository(val application: Application) {

    private val foodDao = DietTrackDatabase.getInstance(application).foodDao
    private val db = FirebaseFirestore.getInstance()

    val foodList = Transformations.map(foodDao.getFoodList()) {
        it.asDomainModel()
    }

    suspend fun saveFoodWithBarcode(barcode: String) {
        withContext(Dispatchers.IO) {
            val networkFood: NetworkFood = OpenFoodApi.retorfitService.getFood(barcode).await()
            val entityFood: EntityFood = networkFood.asDatabaseModel()
            val food:Food = networkFood.asDomainModel()
            foodDao.insert(entityFood)
            db.collection("Llista").add(food)
        }
    }

}