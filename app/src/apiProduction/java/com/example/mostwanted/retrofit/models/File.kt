package com.example.mostwanted.retrofit.models

import com.google.gson.annotations.SerializedName


data class File(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)