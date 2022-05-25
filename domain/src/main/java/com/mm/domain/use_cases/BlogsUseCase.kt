package com.mm.domain.use_cases

import com.mm.domain.model.Blog
import com.mm.domain.model.Output
import kotlinx.coroutines.flow.Flow

interface BlogsUseCase {

    /**
     * UseCase Method to fetch the Blogs from Data Layer
     */
    suspend fun execute(): Flow<Output<List<Blog>>>

}