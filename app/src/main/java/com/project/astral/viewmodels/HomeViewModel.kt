package com.project.astral.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.astral.data.models.apod.ApodResponse
import com.project.astral.data.repository.ApodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apodRepository: ApodRepository
): ViewModel() {
    private val _apodResource = MutableLiveData<ApodResponse>()
    val apodResource: LiveData<ApodResponse> get() = _apodResource

    init {
        loadAPOD()
    }

    private fun loadAPOD() {
        viewModelScope.launch {
            apodRepository.loadAPOD().collect {
                _apodResource.value = it
            }
        }
    }
}