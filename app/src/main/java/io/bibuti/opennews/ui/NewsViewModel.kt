package io.bibuti.opennews.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.bibuti.opennews.core.State
import io.bibuti.opennews.data.apiresponses.NewsResponse
import io.bibuti.opennews.repository.NewsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for News Repository
 */
class NewsListingViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val newsLiveData: MutableLiveData<State<NewsResponse>> by lazy {
        MutableLiveData()
    }

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            newsRepository.getNews().collect { state ->
                newsLiveData.value = state
            }
        }
    }

    fun fetchNewsFromDB() = newsRepository.getNewsFromDB().asLiveData()


}