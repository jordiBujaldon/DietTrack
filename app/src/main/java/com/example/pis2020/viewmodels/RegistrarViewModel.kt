package com.example.pis2020.viewmodels

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrarViewModel : ViewModel(){

    private var _navigateToUserDataEmail = MutableLiveData<Boolean>()
    val navigateToUserDataEmail: LiveData<Boolean>
        get() = _navigateToUserDataEmail

    private var _navigateToUserDataGoogle = MutableLiveData<FirebaseUser>()
    val navigateToUserDataGoogle: LiveData<FirebaseUser>
        get() = _navigateToUserDataGoogle

    fun naviagetToUserDataEmail() {
        _navigateToUserDataEmail.value = true
    }

    fun navigateToUserDataEmailComplete() {
        _navigateToUserDataEmail.value = false
    }

    fun navigateToUserDataGoogle(user: FirebaseUser) {
        _navigateToUserDataGoogle.value = user
    }

    fun navigateToUserDataGoogleComplete() {
        _navigateToUserDataGoogle.value = null
    }

}