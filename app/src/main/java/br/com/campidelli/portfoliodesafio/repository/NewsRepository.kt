package br.com.campidelli.portfoliodesafio.repository

import InitialPageResponse
import FeedResponse
import br.com.campidelli.portfoliodesafio.service.State

interface NewsRepository {
    suspend fun getInitialPage(uri: String): State<InitialPageResponse>
    suspend fun getFeed(uri: String, id: String, page: Int): State<FeedResponse>
}