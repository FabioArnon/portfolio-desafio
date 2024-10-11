package br.com.campidelli.portfoliodesafio.di

import br.com.campidelli.portfoliodesafio.NewsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel(get(), get()) }
}