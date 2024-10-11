package br.com.campidelli.portfoliodesafio.usecase

import InitialPageResponse
import br.com.campidelli.portfoliodesafio.repository.NewsRepository
import br.com.campidelli.portfoliodesafio.service.State

class GetInitialPageNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(uri: String): State<InitialPageResponse> =
        repository.getInitialPage(uri)

}