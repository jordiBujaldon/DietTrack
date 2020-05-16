package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.domain.Food
import com.example.pis2020.domain.User
import com.example.pis2020.repositories.FoodRepository
import com.example.pis2020.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : ViewModel() {

    private val foodRepository = FoodRepository(application)
    private val userRepository = UserRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val scannedFoods: LiveData<List<Food>> = foodRepository.foodList

    private var _user = MutableLiveData<User>()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _user.value = userRepository.getUser()
        }
    }

    fun addFoodData(food: Food) {
        viewModelScope.launch {
            val user = _user.value!!
            user.hidratsCarb += food.product.nutriments.carbonHydrate
            user.fats += food.product.nutriments.fat
            user.proteins += food.product.nutriments.proteins
            user.saturedFats += food.product.nutriments.saturatedFat
            user.sodium += food.product.nutriments.sodium
            user.sugars += food.product.nutriments.sugars

            userRepository.updateUser(user)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
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
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}