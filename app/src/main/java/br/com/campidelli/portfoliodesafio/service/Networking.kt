package br.com.campidelli.portfoliodesafio.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Networking {
    companion object{
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://native-leon.globo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        val service: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)

        private fun getOkHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }
    }
}