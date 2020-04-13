package com.example.pis2020.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pis2020.repositories.UserRepository

class RegistrarViewModel : ViewModel(){

    private val repository = UserRepository()

    fun registrar(email:String, contrasena:String, google:Boolean = false){

        repository.createNewAccount(email,contrasena,false)



    }


}