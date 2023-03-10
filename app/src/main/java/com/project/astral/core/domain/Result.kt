package com.project.astral.core.domain

sealed class Result<T> {

    data class Error<T>(
        val errorMessage: String?
    ) : Result<T>()

    data class Success<T>(
        val data: T? = null
    ) : Result<T>()

    data class Loading<T>(
        val progressState: ProgressState = ProgressState.Idle
    ) : Result<T>()
}

sealed class ProgressState {

    object Progress : ProgressState()

    object Idle : ProgressState()
}