package br.com.campidelli.portfoliodesafio

import InitialPageResponse
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

    init {
        fetchInitialPageNews("g1")
    }

    fun fetchInitialPageNews(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
                _initialPageNews.value = State.Loading
                _initialPageNews.value = getInitialPageNewsUseCase(uri)
            }
    }


    fun refreshFeedNews() {
        fetchInitialPageNews("g1")
    }

}