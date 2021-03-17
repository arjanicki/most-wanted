package com.example.mostwanted.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.mostwanted.MostWantedPagingSource
import com.example.mostwanted.models.ImageUnionType
import com.example.mostwanted.models.MostWantedPerson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class RecipeMockedRepository : MostWantedRepository {

    override fun getMostWantedPeople(): Flow<PagingData<MostWantedPerson>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MostWantedPagingSource() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { item ->
                    MostWantedPerson(
                        item.id,
                        item.name,
                        ImageUnionType.Drawable(item.thumbnailImage),
                        ImageUnionType.Drawable(item.originalImage),
                        item.race,
                        item.nationality,
                        item.weight,
                        item.caution,
                        item.hair,
                        item.eyes,
                        item.description,
                        item.sex,
                        item.placeOfBirth
                    )
                }
            }
}