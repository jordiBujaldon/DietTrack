package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.pis2020.domain.User
import com.example.pis2020.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigateToMainContent = MutableLiveData<Boolean>()
    val navigateToMainContent: LiveData<Boolean>
        get() = _navigateToMainContent

    fun navigateToMainContent() {
        _navigateToMainContent.value = true
    }

    fun navigateToMainContentComplete() {
        _navigateToMainContent.value = false
    }

    fun checkSignedUser(user: User) {
        viewModelScope.launch {
            repository.checkSignedUser(user)
        }
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
            if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
                return SignInViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}