package com.example.mydrinksapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydrinksapp.data.database.entities.IngredientEntity

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredients_table ")
    suspend fun getIngredientById(): List<IngredientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ingredients: List<IngredientEntity>)

    @Query("DELETE FROM ingredients_table")
    suspend fun clearAll()
}