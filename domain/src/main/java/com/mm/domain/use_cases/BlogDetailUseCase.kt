package com.mm.domain.use_cases

import com.mm.domain.model.Blog
import com.mm.domain.model.Output
import kotlinx.coroutines.flow.Flow

interface BlogDetailUseCase {
    suspend fun execute(id: String): Flow<Output<Blog>>
}