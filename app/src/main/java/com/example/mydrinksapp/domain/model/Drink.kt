package com.example.mydrinksapp.domain.model

import com.example.mydrinksapp.data.database.entities.DrinkEntity
import com.example.mydrinksapp.data.model.DrinkModel

data class Drink(
    val id: Int?,
    val name: String?,
    val container: String?,
    val img: String?,
    val instructions: String?,
    val lat: Double?,
    val long: Double?
)

fun DrinkModel.toDomain() = Drink(
    id, name, container, img, instructions, address.lat, address.long
)

fun DrinkEntity.toDomain() = Drink(
    id, name, container, img, instructions, lat, lon
)