package com.example.mostwanted.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mostwanted.repositories.MostWantedRepository

class MostWantedListViewModel(
    private val mostWantedRepository: MostWantedRepository
) : ViewModel() {

    fun getMostWantedPeople() = mostWantedRepository.getMostWantedPeople().cachedIn(viewModelScope)
}