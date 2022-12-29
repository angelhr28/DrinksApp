package com.example.mydrinksapp.domain.usecase

import com.example.mydrinksapp.data.DrinkRepository
import com.example.mydrinksapp.domain.model.DrinkDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDrinkByIdUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: DrinkRepository

    lateinit var getDrinkByIdUseCase: GetDrinkByIdUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDrinkByIdUseCase = GetDrinkByIdUseCase(repository)
    }

    @Test
    fun `verify return value by id`() = runBlocking {
        //Given
        val defaultResponse = DrinkDetail("name", "", 0.0, 0.0, "", "")
        coEvery { repository.getDrinkById(1) } returns defaultResponse

        //When
        val response = getDrinkByIdUseCase(1)

        //Then
        coVerify(exactly = 1) { repository.getDrinkById(any()) }
        assert(defaultResponse == response)
    }

}