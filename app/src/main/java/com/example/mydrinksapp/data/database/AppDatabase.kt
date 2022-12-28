package com.example.mydrinksapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydrinksapp.data.database.dao.DrinkDao
import com.example.mydrinksapp.data.database.dao.IngredientDao
import com.example.mydrinksapp.data.database.entities.DrinkEntity
import com.example.mydrinksapp.data.database.entities.IngredientEntity

@Database(entities = [DrinkEntity::class, IngredientEntity::class], version = 3, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
    abstract fun ingredientDao(): IngredientDao
}