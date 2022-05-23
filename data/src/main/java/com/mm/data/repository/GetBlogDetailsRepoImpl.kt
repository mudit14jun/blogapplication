package com.mm.data.repository

import com.mm.data.mappers.toDomain
import com.mm.data.network.ApiService
import com.mm.data.network.utils.SafeApiRequest
import com.mm.domain.model.Blog
import com.mm.domain.model.Owner
import com.mm.domain.repository.GetBlogDetailsRepo
import javax.inject.Inject

class GetBlogDetailsRepoImpl @Inject constructor(private val apiService: ApiService) :
    GetBlogDetailsRepo, SafeApiRequest() {
    override suspend fun getBlogDetails(id: String): Blog {
        val response = safeApiRequest { apiService.getBlogDetails(id = id) }

        val blog = Blog(
            id = response.id ?: "",
            image = response.image ?: "",
            likes = response.likes ?: 0,
            owner = response.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = response.publishDate ?: "",
            tags = response.tags ?: emptyList(),
            text = response.text ?: ""
        )

        return blog
    }
}