package br.com.campidelli.portfoliodesafio.service

import InitialPageResponse
import FeedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("/feed/{uri}")
    suspend fun getInitialPageNews(@Path("uri") uri: String): Response<InitialPageResponse>
    //A ideia é fazer com que o get de página inicial seja genérico e possa ser usado tanto para a chamada do "g1" quanto para a https://g1.globo.com/economia/agronegocios

    @GET("/feed/page/{uri}/{id}/{page}")
    suspend fun getFeedNews(
        @Path("uri") uri: String,
        @Path("id") id: String,
        @Path("page") page: Int
    ): Response<FeedResponse>
} //segue a mesma lógica da chamada de cima, tentando se manter genérica para ser reutilizada com diferentes uri's