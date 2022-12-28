package com.example.mydrinksapp.data.model


import com.google.gson.annotations.SerializedName

data class IngredientModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String
)