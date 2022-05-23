package com.mm.data.repository

import com.mm.common.Resource
import com.mm.data.mappers.toDomain
import com.mm.data.network.ApiService
import com.mm.domain.model.Blog
import com.mm.domain.repository.GetPagerBlogsRepo
import javax.inject.Inject

class GetPagerBlogsRepoImpl @Inject constructor(private val apiService: ApiService) :
    GetPagerBlogsRepo {

    override suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>> {
        return try {
            val response = apiService.getBlogsPagination(page = page, limit = limit)
            if (response.isSuccessful) {
                val body = response.body()?.data?.toDomain()
                Resource.Success(body)

            } else {
                Resource.Error(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}