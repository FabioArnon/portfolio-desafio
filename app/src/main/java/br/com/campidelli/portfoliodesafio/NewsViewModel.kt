package br.com.campidelli.portfoliodesafio

import FeedResponse
import InitialPageResponse
import Item
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.campidelli.portfoliodesafio.service.State
import br.com.campidelli.portfoliodesafio.usecase.GetFeedNewsUseCase
import br.com.campidelli.portfoliodesafio.usecase.GetInitialPageNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getInitialPageNewsUseCase: GetInitialPageNewsUseCase,
    private val getFeedNewsUseCase: GetFeedNewsUseCase
) : ViewModel() {

    private val _initialPageNews = MutableStateFlow<State<InitialPageResponse>>(State.Loading)
    val initialPageNews: StateFlow<State<InitialPageResponse>> get() = _initialPageNews

    private val _feedNews = MutableStateFlow<List<Item>>(emptyList())
    val feedNews: StateFlow<List<Item>> get() = _feedNews

    private var nextPage: Int? = null
    private var feedId: String? = null

    fun fetchInitialPageNews(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _initialPageNews.value = State.Loading
            val result = getInitialPageNewsUseCase(uri)
            if (result is State.Success) {
                val initialPageResponse = result.data
                feedId = initialPageResponse.feed.oferta
                nextPage = initialPageResponse.feed.falkor?.nextPage
                _feedNews.value = initialPageResponse.feed.falkor?.items ?: emptyList()
                _initialPageNews.value = result
            } else if (result is State.Error) {
                _initialPageNews.value = result
            }
        }
    }

    private fun fetchFeedNews(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentFeedId = feedId
            val currentPage = nextPage
            if (currentFeedId != null && currentPage != null) {
                val result = getFeedNewsUseCase(uri, currentFeedId, currentPage)
                if (result is State.Success) {
                    val feedResponse = result.data
                    nextPage = feedResponse.feed.falkor?.nextPage
                    _feedNews.value += (feedResponse.feed.falkor?.items ?: emptyList())
                } else if (result is State.Error) {
                    _initialPageNews.value = result //Precisaria ser refeito com calma, não faz muito sentido, mas é para não deixar vazio
                }
            }
        }
    }

    fun refreshFeedNews(uri: String) {
        nextPage = 0
        fetchInitialPageNews(uri)
    }

    fun loadMoreNews(uri: String) {
        fetchFeedNews(uri)
    }
}