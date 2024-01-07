package com.donatsu.news.src.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.donatsu.news.src.domain.entity.Article
import com.donatsu.news.src.presentation.page.NewsDetailPage
import com.donatsu.news.src.presentation.page.NewsPage

@Composable
fun NavGraph(startDestination: String = AppRoutes.NewsPage.route) {
    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = startDestination) {

        composable(AppRoutes.NewsPage.route) {
            NewsPage { article ->
                controller.currentBackStackEntry?.savedStateHandle?.set("article", article)
                controller.navigate(AppRoutes.NewsDetailPage.route)
            }
        }

        composable(AppRoutes.NewsDetailPage.route) {
            controller.previousBackStackEntry?.savedStateHandle?.get<Article>("article")?.let {
                NewsDetailPage(it, navigateUp = { controller.navigateUp() })
            }
        }
    }
}
