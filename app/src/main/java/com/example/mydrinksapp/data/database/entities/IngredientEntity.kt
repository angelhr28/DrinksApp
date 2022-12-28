package com.example.mydrinksapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mydrinksapp.data.model.IngredientModel

@Entity(
    tableName = "ingredients_table",
    foreignKeys = [ForeignKey(
        entity = DrinkEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("drink_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "drink_id") val drinkId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: String
)

fun IngredientModel.toDatabase(id: Int) = IngredientEntity(drinkId = id, name = name, quantity = quantity)
