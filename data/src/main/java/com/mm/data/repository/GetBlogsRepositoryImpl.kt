package com.mm.data.repository

import com.mm.data.mappers.toDomain
import com.mm.data.network.ApiService
import com.mm.data.network.utils.SafeApiRequest
import com.mm.domain.model.Blog
import com.mm.domain.repository.GetBlogsRepository
import javax.inject.Inject

class GetBlogsRepositoryImpl @Inject constructor(private val apiService: ApiService): GetBlogsRepository , SafeApiRequest() {

    override suspend fun getBlogs(): List<Blog> {
        val response = safeApiRequest { apiService.getBlogs() }
        return response.data?.toDomain()?: emptyList()
    }



}