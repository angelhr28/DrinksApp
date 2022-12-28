package com.example.mydrinksapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydrinksapp.data.database.entities.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drinks_table ORDER BY id DESC")
    suspend fun getAllDrink(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drinks: List<DrinkEntity>)

    @Query("DELETE FROM drinks_table")
    suspend fun clearAll()

    @Query("DELETE FROM drinks_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM drinks_table WHERE name like '%'|| :search ||'%'")
    suspend fun searchByName(search: String): List<DrinkEntity>
}