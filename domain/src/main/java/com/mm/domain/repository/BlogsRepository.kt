package com.mm.domain.repository

import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.flow.Flow

interface BlogsRepository {

    suspend fun getBlogs(): Flow<OutputResource<List<Blog>>>

}