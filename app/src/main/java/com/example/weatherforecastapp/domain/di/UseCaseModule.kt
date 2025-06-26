package com.example.weatherforecastapp.domain.di

import com.example.weatherforecastapp.data.repository.RepositoryImpl
import com.example.weatherforecastapp.domain.usecase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun currentWeatherUseCaseProvider(repositoryImpl: RepositoryImpl) : GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repositoryImpl)
    }

}