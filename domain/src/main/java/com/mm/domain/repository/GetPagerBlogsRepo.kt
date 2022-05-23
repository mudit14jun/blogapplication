package com.mm.domain.repository

import com.mm.common.Resource
import com.mm.domain.model.Blog

interface GetPagerBlogsRepo {

    suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<Blog>>

}