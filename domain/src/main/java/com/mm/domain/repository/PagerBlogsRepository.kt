package com.mm.domain.repository

import com.mm.domain.model.Blogs
import com.mm.domain.model.Output
import kotlinx.coroutines.flow.Flow

interface PagerBlogsRepository {

    suspend fun getPagerBlogs(page: Int, limit: Int): Flow<Output<Blogs>>

}