package com.example.pis2020.repositories

import android.app.Application
import com.example.pis2020.activities.utils.FirebaseUserLiveData
import com.example.pis2020.database.DietTrackDatabase
import com.example.pis2020.domain.User
import com.example.pis2020.domain.asDatabaseModel
import com.example.pis2020.domain.asDomainModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.withContext

class UserRepository(application: Application) {

    // Referencia a Firebase Authentication
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // LiveData de l'usuari
    val user: FirebaseUserLiveData = FirebaseUserLiveData(auth)

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

}