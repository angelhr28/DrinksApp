package com.example.mydrinksapp.data.model


import com.google.gson.annotations.SerializedName

data class DrinkModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("container")
    val container: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("address")
    val address: AddressModel,
    @SerializedName("ingredients")
    val ingredientModels: List<IngredientModel>
)