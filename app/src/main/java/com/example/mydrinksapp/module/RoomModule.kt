package com.example.mydrinksapp.module

import android.content.Context
import androidx.room.Room
import com.example.mydrinksapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DEVELOPER_DATABASE_NAME = "drinks.db"

    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DEVELOPER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providerDrinkDao(db: AppDatabase) = db.drinkDao()

    @Singleton
    @Provides
    fun providerIngredientDao(db: AppDatabase) = db.ingredientDao()
}