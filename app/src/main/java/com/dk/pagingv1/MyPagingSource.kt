package com.dk.pagingv1

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1
class MyPagingSource : PagingSource<Int, User>(){

    init{
        Log.d("MyPagingSource", "init")
    }

    //페이징이 실행되면 어떻게 할것인지 정하는 부분
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        Log.d("MyPagingSource", "load")
        Log.d("params.loadSize", params.loadSize.toString())
        Log.d("params.key", params.key.toString())

        val page = params.key ?: STARTING_KEY
        Log.d("page", page.toString())

        val range = page.until(page + params.loadSize) // 1 부터 시작해서 30*3 까지
        Log.d("range", range.toString())


        // 처음 로드되는게 아니면 1.5초 딜레이
        if(page != STARTING_KEY){
            delay(1500)
        }

        return LoadResult.Page(
            data = range.map { number ->
            User(
                id = number,
                userName = "userName is $number"
            )
        },
            prevKey = null,
            nextKey = range.last + 1 // 밑으로 당겼을때 마지막 값에서 플러스 1을 해준다.
        )
        }

    // 새로고침을 누르면 어떻게 할것인지 정하는 부분

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}