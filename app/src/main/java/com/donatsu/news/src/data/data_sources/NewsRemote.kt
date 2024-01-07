package com.donatsu.news.src.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.donatsu.news.src.data.model.NewsParams
import com.donatsu.news.src.domain.entity.Article

class NewsRemote(private val api: NewsAPI, private val params: NewsParams) :
    PagingSource<Int, Article>() {
    private var count = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val response = api.getNews(sources = this.params.sources, page = page)
            count += response.articles.size
            val articles = response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (count == response.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}