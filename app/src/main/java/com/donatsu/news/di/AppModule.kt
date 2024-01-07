package com.donatsu.news.di

import com.donatsu.news.core.utils.Constants.BASE_URL
import com.donatsu.news.src.data.data_sources.NewsAPI
import com.donatsu.news.src.data.repository.NewsRepositoryImpl
import com.donatsu.news.src.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): NewsAPI = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(NewsAPI::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: NewsAPI): NewsRepository = NewsRepositoryImpl(api)

}