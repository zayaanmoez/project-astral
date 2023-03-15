package com.project.astral.data.repository

import android.util.Log
import com.project.astral.api.NewsService
import com.project.astral.api.SpaceflightService
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
                articlesListLiveData.addAll(spaceflightResponse.map {
                    Article(
                        title = it.title,
                        url = it.url,
                        imageUrl = it.imageUrl,
                        summary = it.summary,
                        publishedAt = it.publishedAt,
                        source = it.newsSite,
                    )
                })
            } catch (e: Exception) {
                Log.e("NewsRepository", e.toString())
            }

//            try {
//                val newsApiResponse = newsService.getArticles(params.pageSize, params.page)
//                articlesListLiveData.addAll(newsApiResponse.articles.map {
//                    Article(
//                        title = it.title,
//                        url = it.url,
//                        imageUrl = it.urlToImage ?: "",
//                        summary = it.description ?: "",
//                        publishedAt = it.publishedAt,
//                        source = it.source.name,
//                    )
//                })
//            } catch (e: Exception) {
//                Log.e("NewsRepository", e.toString())
//            }

            articlesListLiveData.sortByDescending {
                it.publishedAt
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