package com.example.mydrinksapp.domain.usecase

import com.example.mydrinksapp.data.DrinkRepository
import com.example.mydrinksapp.domain.model.DrinkDetail
import javax.inject.Inject

class GetDrinkByIdUseCase @Inject constructor(
    private val repository: DrinkRepository,
) {
    suspend operator fun invoke(drinkId: Int): DrinkDetail {
        return repository.getDrinkById(drinkId)
    }
}