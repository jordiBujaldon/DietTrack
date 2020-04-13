package com.example.pis2020.viewmodels

import com.example.pis2020.repositories.UserRepository

class SignInViewModel {

    var userRepository: UserRepository = UserRepository()

    fun login (email:String,contrasena:String, google:Boolean = false){

        userRepository.login(email,contrasena,false)

    }


}