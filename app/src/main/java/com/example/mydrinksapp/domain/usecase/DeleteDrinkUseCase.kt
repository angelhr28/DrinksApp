package com.example.mydrinksapp.domain.usecase

import com.example.mydrinksapp.data.DrinkRepository
import javax.inject.Inject

class DeleteDrinkUseCase @Inject constructor(
    private val repository: DrinkRepository,
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteDrink(id = id)
    }
}