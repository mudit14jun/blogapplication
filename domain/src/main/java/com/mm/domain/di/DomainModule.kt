package com.mm.domain.di

import com.mm.domain.repository.GetBlogsRepository
import com.mm.domain.use_cases.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {



    @Provides
    fun provideGetBlogsUseCase(getBlogsRepository: GetBlogsRepository):GetBlogsUseCase{
        return GetBlogsUseCase(getBlogsRepository)
    }

}