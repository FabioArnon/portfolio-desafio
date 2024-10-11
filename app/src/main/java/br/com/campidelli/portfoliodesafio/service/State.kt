package br.com.campidelli.portfoliodesafio.service

sealed class State<out T> {
    data object Loading : State<Nothing>()
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val exception: Throwable) : State<Nothing>()
}