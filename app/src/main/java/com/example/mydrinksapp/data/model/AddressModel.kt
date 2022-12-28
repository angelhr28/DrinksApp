package com.example.mydrinksapp.data.model


import com.google.gson.annotations.SerializedName

data class AddressModel(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("long")
    val long: Double
)