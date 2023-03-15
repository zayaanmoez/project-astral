package com.project.astral.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.astral.data.models.Article
import com.project.astral.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    private val _articleList = MutableLiveData<List<Article>>(mutableListOf())
    val articleList: LiveData<List<Article>> get() = _articleList

    private var loadParams: NewsRepository.LoadParams = NewsRepository.LoadParams(0, 10)

    init {
        loadArticles()
    }

    fun loadArticles(onLoad: () -> Unit = {}) {
        viewModelScope.launch {
            newsRepository.loadArticles(loadParams).collect {
                val updatedFeed = _articleList.value?.toMutableSet()
                updatedFeed?.addAll(it)
                _articleList.value = updatedFeed?.toList()
                loadParams.page += 1
                onLoad()
            }
        }
    }

    fun reloadArticles(onLoad: () -> Unit = {}) {
        loadParams.page = 0
        _articleList.value = mutableListOf()
        loadArticles(onLoad)
    }

}