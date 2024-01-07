package com.donatsu.news.src.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.donatsu.news.src.data.data_sources.NewsAPI
import com.donatsu.news.src.data.data_sources.NewsRemote
import com.donatsu.news.src.data.model.NewsParams
import com.donatsu.news.src.domain.entity.Article
import com.donatsu.news.src.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val api: NewsAPI) : NewsRepository {

    override fun getNews(params: NewsParams): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            NewsRemote(api = api, params = params)
        }).flow
    }

}