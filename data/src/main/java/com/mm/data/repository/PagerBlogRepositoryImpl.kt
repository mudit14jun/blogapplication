package com.mm.data.repository

import com.mm.data.remote.PagerBlogRemoteDataSource
import com.mm.domain.model.Blogs
import com.mm.domain.model.Output
import com.mm.domain.repository.PagerBlogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementation of PagerBlogRepository
 * @param pagerBlogRemoteDataSource the object of remote data source
 */
internal class PagerBlogRepositoryImpl @Inject constructor(
    private val pagerBlogRemoteDataSource: PagerBlogRemoteDataSource
) : PagerBlogsRepository {

    override suspend fun getPagerBlogs(page: Int, limit: Int): Flow<Output<Blogs>> {
        return flow {
            emit(Output.loading())
            val result = pagerBlogRemoteDataSource.getPagerBlogs(page, limit)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}