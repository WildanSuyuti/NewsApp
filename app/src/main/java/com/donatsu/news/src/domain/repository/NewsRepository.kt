package com.donatsu.news.src.domain.repository

import androidx.paging.PagingData
import com.donatsu.news.src.data.model.NewsParams
import com.donatsu.news.src.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(params: NewsParams): Flow<PagingData<Article>>
}