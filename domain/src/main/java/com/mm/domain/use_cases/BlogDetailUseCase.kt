package com.mm.domain.use_cases

import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.flow.Flow

interface BlogDetailUseCase {
    suspend fun execute(id: String): Flow<OutputResource<Blog>>
}