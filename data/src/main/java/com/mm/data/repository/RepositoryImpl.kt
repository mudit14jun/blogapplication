package com.mm.data.repository

import androidx.paging.PagingSource
import com.mm.data.remote.RemoteDataSource
import com.mm.domain.model.Blog
import com.mm.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun getBlogsData(): PagingSource<Int, Blog> {
        return remoteDataSource.getBlogs()
    }
}