package com.example.mydrinksapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mydrinksapp.data.model.DrinkModel
import com.example.mydrinksapp.domain.model.Drink

@Entity(tableName = "drinks_table")
data class DrinkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "img") val img: String?,
    @ColumnInfo(name = "instructions") val instructions: String?,
    @ColumnInfo(name = "container") val container: String?,
    @ColumnInfo(name = "lon") val lon: Double?,
    @ColumnInfo(name = "lat") val lat: Double?
)

fun DrinkModel.toDatabase() = DrinkEntity(id ?: 0, name, img, instructions, container, address.long, address.lat)
