package com.donatsu.news.src.presentation.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.donatsu.news.src.data.model.NewsParams
import com.donatsu.news.src.domain.usecase.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(getNews: GetNews) : ViewModel() {
    val news = getNews(NewsParams()).cachedIn(viewModelScope)
}