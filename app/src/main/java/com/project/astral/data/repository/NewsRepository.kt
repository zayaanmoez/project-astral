package com.project.astral.data.repository

import com.project.astral.api.NewsService
import com.project.astral.api.SpaceflightService
import com.project.astral.core.domain.Result
import com.project.astral.data.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val spaceflightService: SpaceflightService,
    private val newsService: NewsService,
) {
    suspend fun loadArticles(params: LoadParams): Flow<List<Article>> {
        return flow {
            val articlesListLiveData: MutableList<Article> = mutableListOf<Article>();
            try {
                val spaceflightResponse = spaceflightService.getArticles(params.pageSize, params.getStart())

                articlesListLiveData.addAll(spaceflightResponse.map { it ->
                    Article(
                        id = it.id,
                        title = it.title,
                        url = it.url,
                        imageUrl = it.imageUrl,
                        summary = it.summary,
                        publishedAt = it.publishedAt,
                        source = it.newsSite,
                    )
                })

                emit(articlesListLiveData)
            } catch (e: Exception) {
                emit(articlesListLiveData)
            }


            emit(articlesListLiveData)
        }
    }

    data class LoadParams(
        var page: Int,
        var pageSize: Int,
    ) {
        fun getStart():Int {
            return page * pageSize;
        }
    }
}