package com.example.pis2020.activities.utils

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseUserLiveData(private val mAuth: FirebaseAuth) : LiveData<FirebaseUser>() {

    /**
     * Variable que estableix el valor del LiveData i retorna un
     * FirebaseUser o null
     */
    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        value = auth.currentUser
    }

    /**
     * Metode que es crida quan aquesta classe te un observer mirant l'estat
     * de authStateListener, es a dir, de l'estat de l'usuari. Si es null o
     * existeix
     */
    override fun onActive() {
        mAuth.addAuthStateListener(authStateListener)
    }

    override fun onInactive() {
        mAuth.addAuthStateListener(authStateListener)
    }

}