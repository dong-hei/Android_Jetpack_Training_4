package com.dk.pagingv1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    val items: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            MyPagingSource()
        }
    ).flow
        .cachedIn(viewModelScope) // 화면전환을 했을때 생명주기가 초기화 되는것을 방지한다.
}