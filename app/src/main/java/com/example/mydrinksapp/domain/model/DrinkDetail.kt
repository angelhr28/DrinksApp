package com.example.mydrinksapp.domain.model

import com.example.mydrinksapp.data.database.entities.DrinkDetailEntity

data class DrinkDetail(
    val drinkName: String,
    val drinkImg: String,
    val drinkLon: Double,
    val drinkLat: Double,
    val instruction: String,
    val ingredients: String
)

data class Ingredients(
    val ingredientName: String,
    val ingredientQuantity: String
)


fun List<DrinkDetailEntity>.toMapper(): DrinkDetail {
    val drinkFirst = this[0]
    var ingredients = ""

    this.forEach {
        ingredients += " - " + it.ingredientQuantity + it.ingredientName + "\n"
    }

    return DrinkDetail(
        drinkFirst.drinkName,
        drinkFirst.drinkImg,
        drinkFirst.drinkLon.toDouble(),
        drinkFirst.drinkLat.toDouble(),
        drinkFirst.drinkInstruction,
        ingredients
    )

}