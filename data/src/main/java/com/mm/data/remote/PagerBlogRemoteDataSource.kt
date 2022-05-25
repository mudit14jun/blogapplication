package com.mm.data.remote

import com.mm.data.BaseRemoteDataSource
import com.mm.data.network.ApiService
import com.mm.domain.model.Blogs
import com.mm.domain.model.Output
import retrofit2.Retrofit
import javax.inject.Inject

class PagerBlogRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    suspend fun getPagerBlogs(page: Int, limit: Int): Output<Blogs> {
        return getResponse(
            request = { apiService.getBlogsPagination(page = page, limit = limit) },
            defaultErrorMessage = "Error fetching Blogs"
        )
    }
}