package com.project.astral.di

import com.project.astral.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideSpaceflightService(): SpaceflightService {
        return SpaceflightService.create()
    }

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return NewsService.create()
    }

    @Singleton
    @Provides
    fun provideLaunchService(): LaunchService {
        return LaunchService.create()
    }

    @Singleton
    @Provides
    fun provideApodService(): NasaService {
        return NasaService.create()
    }
}