package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.domain.Food
import com.example.pis2020.repositories.FoodRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class AlimentosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoodRepository(application)

    val foodList: LiveData<List<Food>> = repository.foodList

    private val viewModelJob: CompletableJob = SupervisorJob()
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun saveScannedFood(barcode: String) {
        viewModelScope.launch {
            repository.saveFoodWithBarcode(barcode)
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
            if (modelClass.isAssignableFrom(AlimentosViewModel::class.java)) {
                return AlimentosViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}
