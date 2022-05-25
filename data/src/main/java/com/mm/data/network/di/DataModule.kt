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

    /* @Provides
       fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
           return Retrofit.Builder()
               .baseUrl(Constant.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(okHttpClient)
               .build()
       }
   */




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

 /*   @Provides
    fun provideGetBlogsRepository(apiService: ApiService): GetBlogsRepository {
        return GetBlogsRepositoryImpl(apiService = apiService)
    }*/

   /* @Binds
    @Singleton
    internal abstract fun bindCharsRepository(repository: CharsRepositoryImpl): CharsRepository
*/
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): BlogDataBase {
        return BlogDataBase.getInstance(context)
    }

    @Provides
    fun provideDAO(blogDataBase: BlogDataBase): BlogDAO {
        return blogDataBase.getBlogDAO()
    }


  /*  @Provides
    fun provideGetPagerRepo(apiService: ApiService): GetPagerBlogsRepo {
        return GetPagerBlogsRepoImpl(apiService)
    }*/

    /*@Provides
    fun provideGetBlogDetailsRepo(apiService: ApiService): GetBlogDetailsRepo {
        return GetBlogDetailsRepoImpl(apiService)
    }*/
/*

    @Provides
    internal abstract fun  provideGetBlogDetailsRepo(repository: GetBlogsDetailsRepositoryImplementation): GetBlogDetailsRepo

    @Binds
    @Singleton
    internal abstract fun pagerBlogRepository(repository: GetPagerBlogsRepoImplementation): GetPagerBlogsRepo

*/


}