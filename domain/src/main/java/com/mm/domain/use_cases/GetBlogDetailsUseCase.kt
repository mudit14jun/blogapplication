package com.mm.domain.use_cases

import com.mm.common.Resource
import com.mm.domain.model.Blog
import com.mm.domain.repository.GetBlogDetailsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogDetailsUseCase @Inject constructor(private val getBlogDetailsRepo: GetBlogDetailsRepo) {


    operator fun invoke(id: String): Flow<Resource<Blog>> = flow {
        emit(Resource.Loading(""))
        try {
            val response = getBlogDetailsRepo.getBlogDetails(id)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }


    }

}