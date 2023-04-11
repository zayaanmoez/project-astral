package com.project.astral.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.astral.data.models.launch.Launch
import com.project.astral.data.repository.LaunchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val launchRepository: LaunchRepository
): ViewModel() {
    private val _pastLaunchList = MutableLiveData<List<Launch>>(mutableListOf())
    val pastLaunchList: LiveData<List<Launch>> get() = _pastLaunchList
    private var pastLaunchLoadParams = LaunchRepository.LoadParams(0, 10, Launch.Type.PAST)

    private val _upcomingLaunchList = MutableLiveData<List<Launch>>(mutableListOf())
    val upcomingLaunchList: LiveData<List<Launch>> get() = _upcomingLaunchList
    private var upcomingLaunchLoadParams = LaunchRepository.LoadParams(0, 10, Launch.Type.UPCOMING)

    init {
        loadUpcomingLauches()
        loadPastLaunches()
    }

    private fun loadPastLaunches(onLoad: () -> Unit = {}) {
        viewModelScope.launch {
            launchRepository.loadLaunches(pastLaunchLoadParams).collect { it ->
                val launchIds = _pastLaunchList.value?.map { it.id }?.toMutableSet()
                val updatedLaunches = _pastLaunchList.value?.toMutableList()
                it.forEach {
                    if (launchIds?.contains(it.id) != true) {
                        updatedLaunches?.add(it)
                    }
                }
                _pastLaunchList.value = updatedLaunches?.toList()
                pastLaunchLoadParams.page += 1
                onLoad()
            }
        }
    }

    private fun loadUpcomingLauches(onLoad: () -> Unit = {}) {
        viewModelScope.launch {
            launchRepository.loadLaunches(upcomingLaunchLoadParams).collect {it ->
                val launchIds = _upcomingLaunchList.value?.map { it.id }?.toMutableSet()
                val updatedLaunches = _upcomingLaunchList.value?.toMutableList()
                it.forEach {
                    if (launchIds?.contains(it.id) != true) {
                        updatedLaunches?.add(it)
                    }
                }
                _upcomingLaunchList.value = updatedLaunches?.toList()
                upcomingLaunchLoadParams.page += 1
                onLoad()
            }
        }
    }

    fun loadLaunches(type: Launch.Type, onLoad: () -> Unit = {}) {
        if (type == Launch.Type.PAST) {
            loadPastLaunches(onLoad)
        } else if (type == Launch.Type.UPCOMING) {
            loadUpcomingLauches(onLoad)
        }
    }

    fun reloadLaunches(type: Launch.Type, onLoad: () -> Unit = {}) {
        if (type == Launch.Type.PAST) {
            pastLaunchLoadParams.page = 0
            _pastLaunchList.value = mutableListOf()
            loadPastLaunches(onLoad)
        } else if (type == Launch.Type.UPCOMING) {
            upcomingLaunchLoadParams.page = 0
            _upcomingLaunchList.value = mutableListOf()
            loadUpcomingLauches(onLoad)
        }
    }
}