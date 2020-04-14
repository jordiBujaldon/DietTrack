package com.example.pis2020.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    private var _navigateToMainContent = MutableLiveData<Boolean>()
    val navigateToMainContent: LiveData<Boolean>
        get() = _navigateToMainContent

    fun navigateToMainContent() {
        _navigateToMainContent.value = true
    }

    fun navigateToMainContentComplete() {
        _navigateToMainContent.value = false
    }

}