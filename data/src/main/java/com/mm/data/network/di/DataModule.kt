package com.mm.data.network.di

import android.content.Context
import com.mm.common.Constant
import com.mm.data.network.ApiService
import com.mm.data.repository.GetBlogDetailsRepoImpl
import com.mm.data.repository.GetBlogsRepositoryImpl
import com.mm.data.repository.GetPagerBlogsRepoImpl
import com.mm.data.room.BlogDAO
import com.mm.data.room.BlogDataBase
import com.mm.domain.repository.GetBlogDetailsRepo
import com.mm.domain.repository.GetBlogsRepository
import com.mm.domain.repository.GetPagerBlogsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent :: class)
@Module
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideGetBlogsRepository(apiService: ApiService): GetBlogsRepository {
        return GetBlogsRepositoryImpl(apiService = apiService)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BlogDataBase {
        return BlogDataBase.getInstance(context)
    }

    @Provides
    fun provideDAO(blogDataBase: BlogDataBase): BlogDAO {
        return blogDataBase.getBlogDAO()
    }


    @Provides
    fun provideGetPagerRepo(apiService: ApiService): GetPagerBlogsRepo {
        return GetPagerBlogsRepoImpl(apiService)
    }

    @Provides
    fun provideGetBlogDetailsRepo(apiService: ApiService): GetBlogDetailsRepo {
        return GetBlogDetailsRepoImpl(apiService)
    }


}