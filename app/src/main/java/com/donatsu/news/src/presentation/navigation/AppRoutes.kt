package com.donatsu.news.src.presentation.navigation

sealed class AppRoutes(val route: String) {

    data object NewsPage : AppRoutes(route = "news")

    data object NewsDetailPage : AppRoutes(route = "news-detail")

}