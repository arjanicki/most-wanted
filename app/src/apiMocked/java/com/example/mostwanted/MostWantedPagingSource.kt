package com.example.mostwanted

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mostwanted.mocked.Constants
import com.example.mostwanted.mocked.MockItem

class MostWantedPagingSource() : PagingSource<Int, MockItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MockItem> {
        val list = Constants.mockedList

        return LoadResult.Page(
            data = list,
            prevKey = null,
            nextKey = null
        )

//        val page = params.key ?: 1
//        val list = items
//
//        return LoadResult.Page(
//            data = list,
//            prevKey = if(page == 1) null else page - 1,
//            nextKey = if(list.isEmpty()) null else page + 1
//        )
    }

    override fun getRefreshKey(state: PagingState<Int, MockItem>): Int? {
        return null
    }

//    override fun getRefreshKey(state: PagingState<Int, MockItem>): Int? {
//        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id?.toInt() }
//    }
}