package com.mm.domain.use_cases

import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of BlogsUseCaseImpl
 * @param blogsRepository the blog repository object
 */
internal class BlogsUseCaseImpl @Inject constructor(private val blogsRepository: BlogsRepository) :
    BlogsUseCase {

    override suspend fun execute(): Flow<OutputResource<List<Blog>>> {
        return blogsRepository.getBlogs()
    }
}



