package br.com.campidelli.portfoliodesafio.di

import br.com.campidelli.portfoliodesafio.repository.NewsRepository
import br.com.campidelli.portfoliodesafio.repository.NewsRepositoryImpl
import br.com.campidelli.portfoliodesafio.service.Networking
import br.com.campidelli.portfoliodesafio.usecase.GetFeedNewsUseCase
import br.com.campidelli.portfoliodesafio.usecase.GetInitialPageNewsUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single { Networking.service }
    single<NewsRepository> {
        NewsRepositoryImpl(client = get())
    }
    single { GetInitialPageNewsUseCase(repository = get()) }
    single { GetFeedNewsUseCase(repository = get()) }
}