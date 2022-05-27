package com.mm.domain.repository

import androidx.paging.PagingSource
import com.mm.domain.model.Blog

interface Repository {

    fun getBlogsData(): PagingSource<Int, Blog>
}