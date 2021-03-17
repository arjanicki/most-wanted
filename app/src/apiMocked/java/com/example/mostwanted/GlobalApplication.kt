package com.example.mostwanted

import android.app.Application
import com.example.mostwanted.di.mostWantedDetailFragmentModule
import com.example.mostwanted.di.mostWantedListFragmentModule
import com.example.mostwanted.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GlobalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GlobalApplication)
            modules(listOf(repositoriesModule, mostWantedListFragmentModule, mostWantedDetailFragmentModule))
        }
    }
} 