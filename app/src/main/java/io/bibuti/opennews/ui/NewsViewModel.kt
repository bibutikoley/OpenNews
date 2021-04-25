package io.bibuti.opennews.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bibuti.opennews.core.State
import io.bibuti.opennews.repository.NewsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        fetchNews()
    }

    val newsPagedLiveData = newsRepository.getNewsFromDB().toLiveData(50)

    private fun fetchNews() {
        viewModelScope.launch {
            newsRepository.getNews().catch {
                Timber.e("Flow Collector -> $this")
            }.collect { response ->
                when (response) {
                    is State.Loading -> Timber.d("Loading")
                    is State.Success -> Timber.d("Success")
                    is State.ResponseError -> Timber.e("Response Error")
                    is State.ExceptionError -> Timber.e("Local Exception")
                }
            }
        }
    }

}