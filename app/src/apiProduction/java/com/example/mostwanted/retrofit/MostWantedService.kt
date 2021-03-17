package com.example.mostwanted.retrofit

import com.example.mostwanted.retrofit.models.Item
import com.example.mostwanted.retrofit.models.WantedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MostWantedService {

    @GET("@wanted")
    suspend fun getMostWantedList(
        @Query("pageSize") pageItems: Int,
        @Query("page") page: Int
    ): WantedResponse

    @GET("@wanted-person/{id}")
    suspend fun getPersonById(@Path("id") id: String): Response<Item>

}