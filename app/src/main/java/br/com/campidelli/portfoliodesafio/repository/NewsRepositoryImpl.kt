package br.com.campidelli.portfoliodesafio.repository

import FeedResponse
import InitialPageResponse
import br.com.campidelli.portfoliodesafio.service.State
import br.com.campidelli.portfoliodesafio.service.RetrofitInterface
import br.com.campidelli.portfoliodesafio.utils.safeAppCall

class NewsRepositoryImpl(private val client: RetrofitInterface) : NewsRepository {
    override suspend fun getInitialPage(uri: String): State<InitialPageResponse> =
        safeAppCall {
            client.getInitialPageNews(uri)
        }

    override suspend fun getFeed(uri: String, id: String, page: Int): State<FeedResponse> =
        safeAppCall {
            client.getFeedNews(uri, id, page)
        }
}