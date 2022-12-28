package com.example.mydrinksapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydrinksapp.domain.model.Drink
import com.example.mydrinksapp.domain.usecase.DeleteDrinkUseCase
import com.example.mydrinksapp.domain.usecase.GetDrinksUseCase
import com.example.mydrinksapp.domain.usecase.GetRefreshDrinkUseCase
import com.example.mydrinksapp.domain.usecase.SearchDrinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val getDrinksUseCase: GetDrinksUseCase,
    private val getRefreshDrinkUseCase: GetRefreshDrinkUseCase,
    private val deleteDrinksUseCase: DeleteDrinkUseCase,
    private val searchDrinksUSSeCase: SearchDrinkUseCase
) : ViewModel() {

    val isRefresh = MutableLiveData<Boolean>()
    val isProgress = MutableLiveData<Boolean>()
    val snackbar = MutableLiveData<String>()
    val drinkModel = MutableLiveData<List<Drink>>()
    val isEmpty = MutableLiveData<Boolean>()

    fun getDrinks() {
        viewModelScope.launch {
            isProgress.postValue(true)
            try {
                val result = getDrinksUseCase()
                drinkModel.postValue(result)
                isEmpty.postValue(false)
            } catch (e: Exception) {
                snackbar.postValue("Sin resultados")
                isEmpty.postValue(true)
            }
            isProgress.postValue(false)
        }
    }

    fun refreshDrink(isConnected: Boolean) {
        viewModelScope.launch {
            isRefresh.postValue(true)
            try {
                val result = if (isConnected) getRefreshDrinkUseCase() else getDrinksUseCase()
                drinkModel.postValue(result)
                isEmpty.postValue(false)
            } catch (e: Exception) {
                snackbar.postValue("Sin resultados")
                isEmpty.postValue(true)
            }
            isRefresh.postValue(false)
        }
    }

    fun deleteDrink(drink: Drink) {
        viewModelScope.launch {
            drink.id?.let { deleteDrinksUseCase(it) }
        }
    }

    fun searchDrink(newQuery: String) {
        viewModelScope.launch {
            isProgress.postValue(true)
            try {
                if (newQuery.isEmpty()) getDrinks()
                val result = searchDrinksUSSeCase(newQuery)
                drinkModel.postValue(result)
                isEmpty.postValue(false)
            } catch (e: Exception) {
                snackbar.postValue("Sin resultados")
                isEmpty.postValue(true)
            }
            isProgress.postValue(false)

        }
    }

}