package com.mm.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.common.Constant
import com.mm.data.getDummyBlogsData
import com.mm.data.network.ApiService
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PagerBlogRemoteDataSourceTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    private val limit: Int = 1
    private val page: Int = 1

    @Mock
    lateinit var retrofit: Retrofit

    private lateinit var pagerBlogRemoteDataSource: PagerBlogRemoteDataSource


    @Before
    fun setUp() {
        pagerBlogRemoteDataSource = PagerBlogRemoteDataSource(apiService, retrofit)
    }

    @Test
    fun `Given PagerBlog When getBlogsPagination returns Success`() = runBlocking {
        //GIVEN
        val dummyBlogList = OutputResource.success(getDummyBlogsData())
        Mockito.`when`(apiService.getBlogsPagination(Constant.APP_ID, page, limit))
            .thenReturn(Response.success(dummyBlogList.data))
        //WHEN
        val fetchedBlog = pagerBlogRemoteDataSource.getPagerBlogs(page, limit)
        //THEN
        assert(fetchedBlog == dummyBlogList)
    }

    @Test
    fun `Given PagerBlog When getBlogsPagination returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getBlogsPagination(Constant.APP_ID, page, limit))
            .thenThrow(mockitoException)
        //WHEN
        val fetchedBlogs = pagerBlogRemoteDataSource.getPagerBlogs(page, limit)
        //THEN
        assert(fetchedBlogs.message == "Unknown Error")
    }

    @Test
    fun `Given PagerBlog When getBlogsPagination returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(apiService.getBlogsPagination(Constant.APP_ID, page, limit))
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedBlogs = pagerBlogRemoteDataSource.getPagerBlogs(page, limit)
        //THEN
        assert(fetchedBlogs.message == "Unknown Error")
    }


}