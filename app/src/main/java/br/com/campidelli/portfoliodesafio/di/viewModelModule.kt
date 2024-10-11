package br.com.campidelli.portfoliodesafio.di

import br.com.campidelli.portfoliodesafio.NewsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { NewsViewModel(get(), get()) }
}