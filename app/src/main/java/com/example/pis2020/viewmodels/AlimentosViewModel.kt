package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.pis2020.domain.Food
import com.example.pis2020.repositories.FoodRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class AlimentosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoodRepository(application)

    val foodList: LiveData<List<Food>> = repository.foodList

    private val viewModelJob: CompletableJob = SupervisorJob()
    private val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigateToCamera = MutableLiveData<Boolean>()
    val navigateToCamera: LiveData<Boolean>
        get() = _navigateToCamera

    private var _navigateToFoodItem = MutableLiveData<Food>()
    val navigateToFoodItem: LiveData<Food>
        get() = _navigateToFoodItem

    fun saveScannedFood(barcode: String) {
        viewModelScope.launch {
            repository.saveFoodWithBarcode(barcode)
        }
    }

    fun navigateToCamera() {
        _navigateToCamera.value = true
    }

    fun navigateToCameraDone() {
        _navigateToCamera.value = false
    }

    fun navigateToFoodItem(food: Food) {
        _navigateToFoodItem.value = food
    }

    fun navigateToFoodItemDone() {
        _navigateToFoodItem.value = null
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
