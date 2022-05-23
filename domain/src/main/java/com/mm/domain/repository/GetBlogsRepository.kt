package com.mm.domain.repository

import com.mm.domain.model.Blog

interface GetBlogsRepository {

    suspend fun getBlogs():List<Blog>

}