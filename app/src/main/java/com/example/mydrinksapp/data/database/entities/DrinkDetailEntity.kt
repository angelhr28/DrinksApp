package com.example.mydrinksapp.data.database.entities

data class DrinkDetailEntity(
    val drinkName: String,
    val drinkImg: String,
    val drinkLon: String,
    val drinkLat: String,
    val drinkInstruction: String,
    val ingredientName: String,
    val ingredientQuantity: String
)
