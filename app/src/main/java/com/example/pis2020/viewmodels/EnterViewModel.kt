package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.pis2020.activities.utils.FirebaseUserLiveData
import com.example.pis2020.repositories.UserRepository
import java.lang.IllegalArgumentException

class EnterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository =
        UserRepository(application)

    val userLiveData: FirebaseUserLiveData = repository.user

    private var _navigateToSignin = MutableLiveData<Boolean>()
    val navigateToSignin: LiveData<Boolean>
        get() = _navigateToSignin

    private var _navigateToSignup = MutableLiveData<Boolean>()
    val navigateToSignup: LiveData<Boolean>
        get() = _navigateToSignup

    fun navigateToSignin() {
        _navigateToSignin.value = true
    }

    fun navigateToSigininComplete() {
        _navigateToSignin.value = false
    }

    fun navigateToSignup() {
        _navigateToSignup.value = true
    }

    fun navigateToSignupComplete() {
        _navigateToSignup.value = false
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        /**
         * Creates a new instance of the given `Class`.
         *
         *
         *
         * @param modelClass a `Class` whose instance is requested
         * @param <T>        The type parameter for the ViewModel.
         * @return a newly created ViewModel
        </T> */
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EnterViewModel::class.java)) {
                return EnterViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}