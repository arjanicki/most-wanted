package com.example.mostwanted.di

import com.example.mostwanted.OnItemClickListener
import com.example.mostwanted.adapters.MostWantedListAdapter
import com.example.mostwanted.adapters.MostWantedLoadingStateAdapter
import com.example.mostwanted.fragments.MostWantedDetailFragment
import com.example.mostwanted.fragments.MostWantedListFragment
import com.example.mostwanted.models.MostWantedPerson
import com.example.mostwanted.repositories.MostWantedApiRepository
import com.example.mostwanted.repositories.MostWantedRepository
import com.example.mostwanted.retrofit.MostWantedService
import com.example.mostwanted.viewmodels.MostWantedDetailViewModel
import com.example.mostwanted.viewmodels.MostWantedListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideWantedService(get()) }
}

val repositoriesModule = module {
    single { provideRecipeRepository(get()) }
}

fun provideClient(): OkHttpClient = OkHttpClient
    .Builder()
    .readTimeout(5, TimeUnit.SECONDS)
    .writeTimeout(5, TimeUnit.SECONDS)
    .connectTimeout(5, TimeUnit.SECONDS)
    .callTimeout(5, TimeUnit.SECONDS)
    .build()

fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

fun provideRetrofit(client: OkHttpClient, converterFactory: Converter.Factory): Retrofit = Retrofit
    .Builder()
    .addConverterFactory(converterFactory)
    .client(client)
    .baseUrl("https://api.fbi.gov")
    .build()

fun provideWantedService(retrofit: Retrofit): MostWantedService =
    retrofit.create(MostWantedService::class.java)

fun provideRecipeRepository(mostWantedService: MostWantedService): MostWantedRepository =
    MostWantedApiRepository(mostWantedService)

val mostWantedListFragmentModule = module {
    scope<MostWantedListFragment> {
        scoped { (listener: OnItemClickListener<MostWantedPerson>) -> MostWantedListAdapter(listener) }
        scoped { MostWantedLoadingStateAdapter(get()) }
        viewModel { MostWantedListViewModel(get()) }
    }
}

val mostWantedDetailFragmentModule = module {
    scope<MostWantedDetailFragment> {
        viewModel { MostWantedDetailViewModel(get()) }
    }
}
