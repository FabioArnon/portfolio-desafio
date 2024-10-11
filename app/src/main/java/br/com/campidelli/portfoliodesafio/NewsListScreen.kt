package br.com.campidelli.portfoliodesafio

import Item
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import br.com.campidelli.portfoliodesafio.service.State
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import org.koin.compose.viewmodel.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel = koinViewModel()) {
    val feedNews by viewModel.initialPageNews.collectAsState()

    val isRefreshing by remember { derivedStateOf { feedNews is State.Loading } }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { viewModel.refreshFeedNews() }
    ) {
        when (feedNews) {
            is State.Loading -> LoadingNewsListScreen()

            is State.Success -> {
               val newsItems = (feedNews as State.Success).data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(newsItems.feed.falkor!!.items!!) { item ->
                        NewsCard(item) { url ->
                            navController.navigate("webView/$url")
                        }
                    }
                    item {
                        LaunchedEffect(Unit) {
                            viewModel.refreshFeedNews()
                        }
                    }
                }
            }

            is State.Error -> {
                val error = (feedNews as State.Error).exception
                Text(text = error.localizedMessage ?: "Unknown error")
            }
        }
    }
}

@Composable
fun NewsCard(item: Item, onClick: (String) -> Unit) {
    if (item.type == "basico" || item.type == "materia") {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    item.content?.url?.let {
                        val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                        onClick(encodedUrl)
                    }
                },
            colors = CardColors(Color.DarkGray, Color.White, Color.DarkGray, Color.DarkGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                item.content?.chapeu?.label?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.padding(
                            0.dp,
                            8.dp,
                            0.dp,
                            0.dp,
                        )
                    )
                }
                Text(
                    text = item.content?.title ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        0.dp,
                        8.dp,
                        0.dp,
                        0.dp,
                    )
                )
                item.content?.image?.sizes?.xs.let { imageUrl ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .size(Size.ORIGINAL)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape)
                    )
                }
                Text(
                    text = item.content?.summary ?: "",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = item.metadata ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadingNewsListScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(128.dp),
            color = Color.LightGray,
            strokeWidth = 5.dp
        )
    }
}
