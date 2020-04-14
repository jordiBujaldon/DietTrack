package com.example.pis2020.viewmodels

import androidx.lifecycle.*

class RegistrarViewModel : ViewModel(){

    private var _navigateToUserData = MutableLiveData<Boolean>()
    val navigateToUserData: LiveData<Boolean>
        get() = _navigateToUserData

    fun naviagetToUserData() {
        _navigateToUserData.value = true
    }

    fun navigateToUserDataComplete() {
        _navigateToUserData.value = false
    }

}