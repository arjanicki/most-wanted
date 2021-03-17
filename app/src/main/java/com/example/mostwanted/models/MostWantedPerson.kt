package com.example.mostwanted.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MostWantedPerson(
    val id: String,
    val name: String,
    val thumbnailImage: ImageUnionType,
    val originalImage: ImageUnionType,
    val race: String?,
    val nationality: String?,
    val weight: String?,
    val caution: String?,
    val hair: String?,
    val eyes: String?,
    val description: String,
    val sex: String?,
    val placeOfBirth: String?,
) : Parcelable