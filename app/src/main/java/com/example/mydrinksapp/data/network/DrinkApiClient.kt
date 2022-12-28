package com.example.mydrinksapp.data.network

import com.example.mydrinksapp.data.model.DrinkResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface DrinkApiClient {
    @GET("drinks")
    suspend fun getAllDrinks(): Response<DrinkResponseModel>
}