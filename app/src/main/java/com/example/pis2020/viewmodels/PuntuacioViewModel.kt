package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.repositories.UserRepository
import java.lang.IllegalArgumentException

class PuntuacioViewModel(application: Application) : ViewModel() {

    private val repository = UserRepository(application)

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