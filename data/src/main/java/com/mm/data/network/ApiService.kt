package com.mm.data.network

import com.mm.common.Constant
import com.mm.domain.model.Blog
import com.mm.domain.model.Blogs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("post")
    suspend fun getBlogs(
        @Header("app-id") appId: String = Constant.APP_ID
    ): Response<Blogs>


    @GET("post")
    suspend fun getBlogsPagination(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<Blogs>

    @GET("post/{id}")
    suspend fun getBlogDetails(
        @Header("app-id") appId: String = Constant.APP_ID,
        @Path("id") id: String
    ): Response<Blog>

}