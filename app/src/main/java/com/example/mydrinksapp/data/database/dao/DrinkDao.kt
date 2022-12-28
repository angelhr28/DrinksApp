package com.example.mydrinksapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydrinksapp.data.database.entities.DrinkDetailEntity
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

    @Query(
        """
        SELECT d.name as drinkName,
               d.img as drinkImg,
               d.lon as drinkLon,
               d.lat as drinkLat,
               d.instructions as drinkInstruction, 
               i.name as ingredientName,
               i.quantity as ingredientQuantity
        FROM drinks_table AS d
        INNER JOIN ingredients_table AS i ON d.id = i.drink_id
        WHERE d.id = :drinkId
        """
    )
    suspend fun getDrinkDetail(drinkId: Int): List<DrinkDetailEntity>
}