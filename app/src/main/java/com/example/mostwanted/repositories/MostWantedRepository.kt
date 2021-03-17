package com.example.mostwanted.repositories

import androidx.paging.PagingData
import com.example.mostwanted.models.MostWantedPerson
import kotlinx.coroutines.flow.Flow

interface MostWantedRepository {

    fun getMostWantedPeople(): Flow<PagingData<MostWantedPerson>>
}