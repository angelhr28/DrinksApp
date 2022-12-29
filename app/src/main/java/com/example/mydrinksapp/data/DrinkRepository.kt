package com.example.mydrinksapp.data

import android.util.Log
import com.example.mydrinksapp.data.database.dao.DrinkDao
import com.example.mydrinksapp.data.database.dao.IngredientDao
import com.example.mydrinksapp.data.database.entities.DrinkEntity
import com.example.mydrinksapp.data.database.entities.IngredientEntity
import com.example.mydrinksapp.data.database.entities.toDatabase
import com.example.mydrinksapp.data.network.DrinkService
import com.example.mydrinksapp.domain.model.Drink
import com.example.mydrinksapp.domain.model.DrinkDetail
import com.example.mydrinksapp.domain.model.toDomain
import com.example.mydrinksapp.domain.model.toMapper
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val drinkService: DrinkService,
    private val drinkDao: DrinkDao,
    private val ingredientDao: IngredientDao
) {

    suspend fun getAllDrinks(): List<Drink> {
        val drinks = getAllDrinksFromDatabase()
        if (drinks.isNotEmpty()) return drinks
        return getAllDrinksFromApi()
    }

    suspend fun getAllDrinksFromApi(): List<Drink> {
        val drinkRemote = drinkService.getDrinks()
        drinkRemote?.apply {
            clearDrinks()
            insertDrinks(this.map { it.toDatabase() })
            this.forEach {
                insertIngredients(it.ingredientModels.map { ingredientModel ->
                    ingredientModel.toDatabase(it.id)
                })
            }
        }?.map {
            it.toDomain()
        }?.let {
            return it
        } ?: kotlin.run {
            throw Exception()
        }
    }

    private suspend fun insertIngredients(ingredients: List<IngredientEntity>) {
        ingredientDao.insertAll(ingredients)
    }

    private suspend fun getAllDrinksFromDatabase(): List<Drink> {
        val response = drinkDao.getAllDrink()
        return response.map(DrinkEntity::toDomain)
    }

    private suspend fun insertDrinks(Drinks: List<DrinkEntity>) {
        drinkDao.insertAll(Drinks)
    }

    private suspend fun clearDrinks() {
        drinkDao.clearAll()
    }

    suspend fun deleteDrink(id: Int) {
        drinkDao.deleteById(id)
    }

    suspend fun searchDrinks(search: String): List<Drink> {
        val result = drinkDao.searchByName(search).map(DrinkEntity::toDomain)
        if (result.isEmpty()) throw Exception()
        return result
    }

    suspend fun getDrinkById(drinkId: Int): DrinkDetail {
        val drinkDetail = drinkDao.getDrinkDetail(drinkId)
        if (drinkDetail.isEmpty()) throw Exception()
        return drinkDetail.toMapper()
    }
}