package br.com.campidelli.portfoliodesafio.application

import android.app.Application
import br.com.campidelli.portfoliodesafio.di.repositoryModule
import br.com.campidelli.portfoliodesafio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PortfolioDesafioApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PortfolioDesafioApplication)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}