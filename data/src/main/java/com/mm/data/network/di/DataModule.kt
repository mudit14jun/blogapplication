package com.mm.data.network.di

import android.content.Context
import com.mm.common.Constant
import com.mm.data.network.ApiService
/*import com.mm.data.repository.GetBlogDetailsRepoImpl
import com.mm.data.repository.GetPagerBlogsRepoImpl*/
import com.mm.data.room.BlogDAO
import com.mm.data.room.BlogDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent :: class)
@Module
object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).
        addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BlogDataBase {
        return BlogDataBase.getInstance(context)
    }

    @Provides
    fun provideDAO(blogDataBase: BlogDataBase): BlogDAO {
        return blogDataBase.getBlogDAO()
    }

}