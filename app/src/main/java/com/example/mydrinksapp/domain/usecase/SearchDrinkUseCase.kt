package com.example.mydrinksapp.domain.usecase

import com.example.mydrinksapp.data.DrinkRepository
import com.example.mydrinksapp.domain.model.Drink
import javax.inject.Inject

class SearchDrinkUseCase @Inject constructor(
    private val repository: DrinkRepository,
) {
    suspend operator fun invoke(search: String): List<Drink> {
        return repository.searchDrinks(search)
    }
}