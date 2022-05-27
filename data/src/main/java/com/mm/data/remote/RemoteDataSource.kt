package com.mm.data.remote

import androidx.paging.PagingSource
import com.mm.data.room.BlogDAO
import com.mm.domain.model.Blog
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val blogDAO: BlogDAO
) {

    fun getBlogs(): PagingSource<Int, Blog> {
        return blogDAO.getAllBlogItems()
    }
}