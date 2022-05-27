package com.mm.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.data.getDummyBlogsData
import com.mm.data.remote.PagerBlogRemoteDataSource
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.PagerBlogsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PagerBlogRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val limit: Int = 1
    private val page: Int = 1

    private lateinit var pagerBlogsRepository: PagerBlogsRepository

    @Mock
    private lateinit var pagerBlogRemoteDataSource: PagerBlogRemoteDataSource

    @Before
    fun setUp() {
        pagerBlogsRepository = PagerBlogRepositoryImpl(pagerBlogRemoteDataSource)
    }

    @Test
    fun `Given Blogs When GetPagerBlogs returns Success`() = runBlocking {
        //GIVEN
        val givenBlogs = getDummyBlogsData()
        val givenBlogsOutput = OutputResource.success(givenBlogs)
        val inputFlow = listOf(OutputResource.loading(), OutputResource.success(givenBlogs))
        Mockito.`when`(pagerBlogRemoteDataSource.getPagerBlogs(page, limit))
            .thenReturn(givenBlogsOutput)

        //WHEN
        val outputFlow = pagerBlogsRepository.getPagerBlogs(page, limit).toList()

        //THEN
        assert(outputFlow.size == 2)
        assert(inputFlow[0] == outputFlow[0])
        assert(inputFlow[1] == outputFlow[1])
    }


}