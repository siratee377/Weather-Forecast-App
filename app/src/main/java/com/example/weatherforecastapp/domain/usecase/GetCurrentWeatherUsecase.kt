package com.example.weatherforecastapp.domain.usecase

import com.example.weatherforecastapp.core.common.UiState
import com.example.weatherforecastapp.data.repository.RepositoryImpl
import com.example.weatherforecastapp.domain.model.CurrentWeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val repositoryImpl : RepositoryImpl)  {

    operator fun invoke(long : String, lat: String) : Flow<UiState<CurrentWeatherData>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = repositoryImpl.getCurrentWeatherAPI(long,lat)))
        }catch (e : Exception){
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}