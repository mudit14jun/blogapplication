package com.mm.domain.use_cases

import com.mm.domain.model.Blog
import com.mm.domain.model.Output
import com.mm.domain.repository.BlogDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Implementation of BlogDetailUseCaseImpl
 * @param blogsDetailsRepository the blog repository object
 */
internal class BlogDetailUseCaseImpl @Inject constructor(private val blogsDetailsRepository: BlogDetailsRepository) :
    BlogDetailUseCase {

    override suspend fun execute(id: String): Flow<Output<Blog>> {
        return blogsDetailsRepository.getBlogDetails(id)
    }
}