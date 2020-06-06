package com.example.pis2020.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.domain.User
import com.example.pis2020.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PerfilViewModel(application: Application) : ViewModel() {

    private val repository = UserRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private var _navigateToEnterFragment = MutableLiveData<Boolean>()
    val navigaetToEnterFragment: LiveData<Boolean>
        get() = _navigateToEnterFragment

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _user.value = repository.getUser()
        }
    }

    fun updatePhotoUser(photo: String) {
        viewModelScope.launch {
            _user.value?.photo = photo
            repository.updateUser(_user.value!!)
        }
    }

    fun navigateToEnterFragment() {
        _navigateToEnterFragment.value = true
    }

    fun navigateToEnterFragmentComplete() {
        _navigateToEnterFragment.value = false
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
            if (modelClass.isAssignableFrom(PerfilViewModel::class.java)) {
                return PerfilViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}