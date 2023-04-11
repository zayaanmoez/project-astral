package com.project.astral.data.repository

import android.util.Log
import com.project.astral.api.LaunchService
import com.project.astral.api.SpaceflightService
import com.project.astral.data.models.Article
import com.project.astral.data.models.launch.Launch
import com.project.astral.data.models.launch.LaunchResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaunchRepository @Inject constructor(
    private val launchService: LaunchService,
    private val spaceflightService: SpaceflightService,
) {

    suspend fun loadLaunches(params: LoadParams): Flow<List<Launch>> {
        return flow {
            val launchListLiveData: MutableList<Launch> = mutableListOf<Launch>();
            try {
                val launchResponse: LaunchResponse = if (params.type == Launch.Type.UPCOMING) {
                    launchService.getUpcomingLaunches(params.pageSize, params.getOffset());
                } else {
                    launchService.getPastLaunches(params.pageSize, params.getOffset());
                }
                launchListLiveData.addAll(launchResponse.results)
            } catch (e: Exception) {
                Log.e("LaunchRepository", e.toString())
            }

            launchListLiveData.forEach { launch ->
                try {
                    val spaceFlightResponse = spaceflightService.getLaunchArticles(launch.id)
                    launch.articles = spaceFlightResponse.map {
                        Article(
                            title = it.title,
                            url = it.url,
                            imageUrl = it.imageUrl,
                            summary = it.summary,
                            publishedAt = it.publishedAt,
                            source = it.newsSite,
                        )
                    }
                } catch (e: Exception) {
                    Log.e("LaunchRepository", e.toString())
                }
            }

            emit(launchListLiveData)
        }
    }

    data class LoadParams(
        var page: Int,
        var pageSize: Int,
        var type: Launch.Type,
    ) {
        fun getOffset():Int {
            return page * pageSize;
        }
    }
}