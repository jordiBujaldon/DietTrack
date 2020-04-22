package com.example.pis2020.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.domain.Food
import java.lang.IllegalArgumentException

class PuntuacionAlimentoViewModel(food: Food?) : ViewModel() {

    private var _selectedFood = MutableLiveData<Food>()
    val selectedFood: LiveData<Food>
        get() = _selectedFood

    init {
        _selectedFood.value = food
    }

    class Factory(private val food: Food?) : ViewModelProvider.Factory {
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
            if (modelClass.isAssignableFrom(PuntuacionAlimentoViewModel::class.java)) {
                return PuntuacionAlimentoViewModel(food) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}