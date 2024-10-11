package br.com.campidelli.portfoliodesafio.utils

import retrofit2.Response
import br.com.campidelli.portfoliodesafio.service.State

suspend fun <T> safeAppCall(call: suspend () -> Response<T>): State<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let {
                State.Success(it)
            } ?: failure(response)
        } else failure(response)
    } catch (e: Exception) {
        State.Error(e)
    }
}

private fun <T> failure(response: Response<T>) =
    State.Error(Throwable("${response.code()} ${response.message()}"))