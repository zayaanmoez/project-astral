package com.project.astral.data.repository

import android.util.Log
import com.project.astral.api.NasaService
import com.project.astral.data.models.apod.ApodResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val nasaService: NasaService
) {
    suspend fun loadAPOD(): Flow<ApodResponse> {
        return flow {
            var apodLiveData: ApodResponse;
            try {
                val apodResponse = nasaService.getAPOD()
                apodLiveData = apodResponse
                
                emit(apodLiveData)
            } catch (e: Exception) {
                Log.e("ApodRepository", e.toString())
            }
        }
    }
}