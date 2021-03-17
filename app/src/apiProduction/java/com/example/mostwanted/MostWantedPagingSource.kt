package com.example.mostwanted

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mostwanted.retrofit.MostWantedService
import com.example.mostwanted.retrofit.models.Item
import retrofit2.HttpException
import java.io.IOException

class MostWantedPagingSource(
    private val service: MostWantedService
) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            val currentNumber  = params.key ?: 1
            val response = service.getMostWantedList(params.loadSize, currentNumber)
            val list = response.items

            return LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = currentNumber + 1
            )
        }
        catch (exception: IOException) {
            Log.d("MostWantedPagingSource", "IOException")
            return LoadResult.Error(exception)
        }
        catch (exception: HttpException) {
            Log.d("MostWantedPagingSource", "HttpException")
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}