package com.donatsu.news.src.data.data_sources

import com.donatsu.news.core.utils.Constants.API_KEY
import com.donatsu.news.core.utils.Constants.COUNTRY
import com.donatsu.news.src.domain.entity.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String?,
        @Query("country") country: String = COUNTRY,
        @Query("apiKey") apiKey: String = API_KEY
    ): News
}