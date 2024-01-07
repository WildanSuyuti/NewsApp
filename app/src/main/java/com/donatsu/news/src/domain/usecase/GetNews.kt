package com.donatsu.news.src.domain.usecase

import androidx.paging.PagingData
import com.donatsu.news.src.data.model.NewsParams
import com.donatsu.news.src.domain.entity.Article
import com.donatsu.news.src.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(params: NewsParams): Flow<PagingData<Article>> {
        return repository.getNews(params)
    }
}