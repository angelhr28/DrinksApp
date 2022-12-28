package com.example.mydrinksapp.data.network

import com.example.mydrinksapp.data.model.DrinkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DrinkService @Inject constructor(
    private val api: DrinkApiClient,
) {
    suspend fun getDrinks(): List<DrinkModel>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllDrinks().body()
                response
            } catch (e: Exception) {
                throw e
            }
        }
    }
}