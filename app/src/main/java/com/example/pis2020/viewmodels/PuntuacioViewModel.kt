package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.domain.User
import com.example.pis2020.repositories.UserRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class PuntuacioViewModel(application: Application) : ViewModel() {

    private val repository = UserRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _user.value = repository.getUser()
        }
    }

    fun resetData() {
        viewModelScope.launch {
            _user.value?.calories = 0.0
            _user.value?.hidratsCarb = 0.0
            _user.value?.proteins = 0.0
            _user.value?.saturedFats = 0.0
            _user.value?.fats = 0.0
            _user.value?.sodium = 0.0
            _user.value?.sugars = 0.0
            repository.updateUser(_user.value!!)
            getUser()
        }
    }

    @Suppress("UNCHECKED_CAST")
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
            if (modelClass.isAssignableFrom(PuntuacioViewModel::class.java)) {
                return PuntuacioViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModle")
        }

    }

}