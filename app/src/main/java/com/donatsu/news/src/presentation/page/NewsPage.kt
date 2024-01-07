package com.donatsu.news.src.presentation.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.donatsu.news.R
import com.donatsu.news.src.domain.entity.Article
import com.donatsu.news.src.presentation.common.NewsScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsPage(
    modifier: Modifier = Modifier, viewModel: NewsViewModel = hiltViewModel(),
    navigateToDetails: (Article) -> Unit
) {
    val articles = viewModel.news.collectAsLazyPagingItems()

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") {
                        it.title.toString()
                    }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = titles,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = modifier.height(24.dp))

        NewsScreen(
            modifier = modifier.padding(horizontal = 24.dp),
            articles = articles,
        ) { article -> navigateToDetails(article) }
    }
}