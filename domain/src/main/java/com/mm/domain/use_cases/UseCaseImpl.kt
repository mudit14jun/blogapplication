package com.mm.domain.use_cases

import androidx.paging.PagingSource
import com.mm.domain.model.Blog
import com.mm.domain.repository.Repository
import javax.inject.Inject

internal class UseCaseImpl @Inject constructor(private val repository: Repository) :
    UseCase {

    override fun execute(): PagingSource<Int, Blog> {
        return repository.getBlogsData()
    }
}