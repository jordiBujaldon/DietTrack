package com.example.pis2020.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pis2020.repositories.UserRepository

class SignInViewModel : ViewModel() {

    var userRepository: UserRepository = UserRepository()


    fun login (email:String,contrasena:String, google:Boolean = false){

        userRepository.login(email,contrasena,false)

    }




}