package com.example.pis2020.repositories

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserRepository() {

    var user: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth

    init{

        auth = FirebaseAuth.getInstance()

    }

    fun createNewAccount(email:String, contrasena:String, google:Boolean) : FirebaseUser? {



            auth.createUserWithEmailAndPassword(email,contrasena).addOnCompleteListener(){

                if(it.isSuccessful){

                    user = auth.currentUser

                }


            }

        return user

    }

    fun login (email:String, contrasena: String, google:Boolean){

        auth.signInWithEmailAndPassword(email,contrasena)

    }


}