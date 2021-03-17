package com.example.mostwanted.di

import com.example.mostwanted.OnItemClickListener
import com.example.mostwanted.adapters.MostWantedListAdapter
import com.example.mostwanted.adapters.MostWantedLoadingStateAdapter
import com.example.mostwanted.fragments.MostWantedDetailFragment
import com.example.mostwanted.fragments.MostWantedListFragment
import com.example.mostwanted.models.MostWantedPerson
import com.example.mostwanted.repositories.MostWantedRepository
import com.example.mostwanted.repositories.RecipeMockedRepository
import com.example.mostwanted.viewmodels.MostWantedDetailViewModel
import com.example.mostwanted.viewmodels.MostWantedListViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoriesModule = module {
    single { provideMostWantedRepository() }
}

fun provideMostWantedRepository(): MostWantedRepository = RecipeMockedRepository()


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