package com.example.pis2020.repositories

import android.app.Application

import com.example.pis2020.activities.utils.FirebaseUserLiveData
import com.example.pis2020.database.DietTrackDatabase
import com.example.pis2020.database.entities.EntityUser
import com.example.pis2020.domain.User
import com.example.pis2020.domain.asDatabaseModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 */
class UserRepository(application: Application) {

    // Referencia a Firebase Authentication
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // LiveData de l'usuari
    val liveUser: FirebaseUserLiveData = FirebaseUserLiveData(auth)

    // Base de dades
    private val userDao = DietTrackDatabase.getInstance(application).userDao

    /**
     * Crea un nou usuari i l'afegeix a la base de dades local i a la de
     * Firebase.
     */
    suspend fun createAccount(id: String, email: String, password: String, username: String,
                              age: Int, height: Double, weight: Double) {
        withContext(Dispatchers.IO) {
            val user = User(id, email, password, username, age, height, weight)
            userDao.insert(user.asDatabaseModel())
            db.collection("users").document(email).set(user)
        }
    }

    suspend fun checkSignedUser(user: User) {
        if (userDao.getUser(auth.uid!!) != null) {
            withContext(Dispatchers.IO) {
                userDao.insert(user.asDatabaseModel())
            }
        }
    }

}