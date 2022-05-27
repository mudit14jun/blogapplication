package com.mm.domain.use_cases

import androidx.paging.PagingSource
import com.mm.domain.model.Blog

interface UseCase {
    fun execute(): PagingSource<Int, Blog>
}