package com.mm.domain.repository

import com.mm.domain.model.Blog
import com.mm.domain.model.Output
import kotlinx.coroutines.flow.Flow

interface BlogDetailsRepository {

    suspend fun getBlogDetails(id: String): Flow<Output<Blog>>

}