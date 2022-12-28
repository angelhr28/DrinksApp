package com.example.mydrinksapp.domain.usecase

import com.example.mydrinksapp.data.DrinkRepository
import com.example.mydrinksapp.domain.model.Drink
import javax.inject.Inject

class GetRefreshDrinkUseCase @Inject constructor(
    private val repository: DrinkRepository,
) {
    suspend operator fun invoke(): List<Drink> {
        return repository.getAllDrinksFromApi()
    }
}