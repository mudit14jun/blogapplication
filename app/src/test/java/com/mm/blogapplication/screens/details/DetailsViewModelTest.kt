package com.mm.blogapplication.screens.details

import androidx.lifecycle.SavedStateHandle
import com.mm.blogapplication.BaseViewModelTest
import com.mm.blogapplication.runBlockingMainTest
import com.mm.blogapplication.getDummyBlog
import com.mm.domain.model.Blog
import com.mm.domain.model.Output
import com.mm.domain.use_cases.BlogDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var blogDetailUseCase: BlogDetailUseCase
    @Mock
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        savedStateHandle.set("blogId","1")
        detailsViewModel = DetailsViewModel(blogDetailUseCase,savedStateHandle)
    }

    @Test
    fun `Given BlogDetail when getBlogDetail should return Success`() = runBlockingMainTest {
        //GIVEN
        val flowBlogs = flowOf(Output.success(getDummyBlog()))
        //WHEN
        Mockito.doReturn(flowBlogs).`when`(blogDetailUseCase).execute(getDummyBlog().id)
        detailsViewModel.getBlogDetails(getDummyBlog().id)
       var blog =   detailsViewModel.details.value.data
        assert(blog?.id?.isNotEmpty() == true)
    }
}