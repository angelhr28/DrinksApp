package com.example.mydrinksapp.domain.usecase

import com.example.mydrinksapp.data.DrinkRepository
import com.example.mydrinksapp.domain.model.Drink
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDrinksUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: DrinkRepository

    lateinit var getDrinksUseCase: GetDrinksUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDrinksUseCase = GetDrinksUseCase(repository)
    }

    @Test
    fun `when the api doesn't return anything the get values from database`() = runBlocking {
        //Given
        coEvery { repository.getAllDrinks() } returns emptyList()

        //When
        getDrinksUseCase()

        //Then
        coVerify { repository.getAllDrinks() }
    }

}