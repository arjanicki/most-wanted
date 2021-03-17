package com.example.mostwanted.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class ImageUnionType: Parcelable {
    @Parcelize
    data class Url(val url: String) : ImageUnionType(), Parcelable

    @Parcelize
    data class Drawable(val resourceId: Int) : ImageUnionType(), Parcelable
}