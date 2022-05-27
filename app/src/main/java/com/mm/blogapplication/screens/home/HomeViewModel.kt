package com.mm.blogapplication.screens.home

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mm.blogapplication.screens.base.BaseViewModel
import com.mm.data.paging.BlogRemoteMediator
import com.mm.data.room.BlogDAO
import com.mm.domain.repository.PagerBlogsRepository
import com.mm.domain.use_cases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCase,
    pagerBlogsRepository: PagerBlogsRepository,
    private val blogDAO: BlogDAO
) :
    BaseViewModel() {


    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        remoteMediator = BlogRemoteMediator(
            pagerBlogsRepository = pagerBlogsRepository,
            blogDAO = blogDAO
        )
    ) {
        useCase.execute()
    }.flow.cachedIn(viewModelScope)
}