package com.example.mostwanted.retrofit.models


import com.google.gson.annotations.SerializedName

data class WantedResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total")
    val total: Int
)