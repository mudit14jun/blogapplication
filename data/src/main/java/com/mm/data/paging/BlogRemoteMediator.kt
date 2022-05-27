package com.mm.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mm.data.room.BlogDAO
import com.mm.data.room.BlogKey
import com.mm.domain.model.Blog
import com.mm.domain.repository.PagerBlogsRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val pagerBlogsRepository: PagerBlogsRepository,
    private val blogDAO: BlogDAO
) : RemoteMediator<Int, Blog>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Blog>): MediatorResult {

        return try {

            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    remoteKeys?.next ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosetKey(state)
                    remoteKeys?.next?.minus(1) ?: initialPage

                }
            }

            pagerBlogsRepository.getPagerBlogs(page = page, limit = state.config.pageSize)
            MediatorResult.Success(true)
        } catch (E: Exception) {

            MediatorResult.Error(E)

        }
    }

    suspend fun getLastKey(state: PagingState<Int, Blog>): BlogKey? {
        return state.lastItemOrNull()?.let {
            blogDAO.getAllKeys(it.id)
        }
    }

    suspend fun getClosetKey(state: PagingState<Int, Blog>): BlogKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                blogDAO.getAllKeys(it.id)
            }
        }
    }

}













