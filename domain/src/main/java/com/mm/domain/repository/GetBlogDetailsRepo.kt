package com.mm.domain.repository

import com.mm.domain.model.Blog

interface GetBlogDetailsRepo {

    suspend fun getBlogDetails(id: String): Blog

}