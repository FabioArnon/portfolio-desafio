package br.com.campidelli.portfoliodesafio.usecase

import FeedResponse
import br.com.campidelli.portfoliodesafio.repository.NewsRepository
import br.com.campidelli.portfoliodesafio.service.State

class GetFeedNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(uri: String, id: String, page: Int): State<FeedResponse> =
        repository.getFeed(uri, id, page)

}