package com.example.mostwanted

import android.app.Application
import com.example.mostwanted.di.mostWantedDetailFragmentModule
import com.example.mostwanted.di.mostWantedListFragmentModule
import com.example.mostwanted.di.networkModule
import com.example.mostwanted.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin

class GlobalApplication : Application() {

    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GlobalApplication)
            fragmentFactory()
            androidLogger()
            modules(
                listOf(
                    networkModule,
                    repositoriesModule,
                    mostWantedListFragmentModule,
                    mostWantedDetailFragmentModule
                )
            )
        }
    }
}