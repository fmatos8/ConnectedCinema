package pt.fabiomatos.connectedcinema.ui.views.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.models.Results
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.ui.views.Homepage
import pt.fabiomatos.connectedcinema.utils.Utils
import pt.fabiomatos.connectedcinema.viewmodel.MoviesViewModel

@Composable
fun HomeScreen(viewModel: MoviesViewModel = viewModel()) {

    val trending by viewModel.trending.observeAsState(initial = emptyList())
    val upcoming by viewModel.upcoming.observeAsState(initial = emptyList())
    val toprated by viewModel.toprated.observeAsState(initial = emptyList())

    val scrollState = rememberScrollState()

    ConnectedCinemaTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (
                modifier = Modifier
                    .verticalScroll(state = scrollState)
            ) {
                TrendingList(trending.take(n = 5))
                HomepageHorizontalList(upcoming, stringResource(id = R.string.upcoming))
                HomepageHorizontalList(toprated, stringResource(id = R.string.top_rated))
            }
        }
    }

}

@Composable
fun TrendingList(items: List<Results>) {
    ImageSlider(items = items)
}



@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomepageHorizontalList(items: List<Results>, label: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(Color.Transparent)
                    .weight(1f)
            )
            Text(
                text = "View all",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .background(Color.Transparent),
                textAlign = TextAlign.End
            )
        }
        LazyRow (
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            items(items) { item ->

                ShadowedImageCard(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500${item.posterPath}"
                    ),
                    contentDescription = item.title.toString(),
                    modifier = Modifier
                        .size(150.dp, 220.dp)
                )
            }
        }
    }
}

@Composable
fun ShadowedImageCard(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0.5f * 200.dp.value, // Start gradient at the middle
                        endY = 500.dp.value // End gradient at the bottom
                    )
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = contentDescription,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            )
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageItem(item: Results) {
    Box {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500${item.posterPath}"
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0.7f, // Start gradient at the middle
                        endY = 500.dp.value // End gradient at the bottom
                    )
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(bottom = 80.dp)
            ) {
                Text(
                    text = item.title ?: item.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .background(Color.Transparent), // Background color to show the shadow clearly
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    val data = item.releaseDate ?: item.firstAirDate.toString()
                    Text(
                        text = Utils.convertStringDateToYear(data),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .background(Color.Transparent), // Background color to show the shadow clearly
                    )

                    Icon(
                        Icons.Filled.Star,
                        tint = MaterialTheme.colorScheme.tertiary,
                        contentDescription = "Email",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = Utils.getRatingFromAverage(item.voteAverage),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .background(Color.Transparent), // Background color to show the shadow clearly
                    )

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(items: List<Results>) {

    // Used Int.MAX_VALUE for infinity scroll
    val pageCount = items.size

    // Init the PagerState with a very large number and make it always start from the first item of the real list
    val pagerState = rememberPagerState(
        pageCount = { pageCount },
        initialPage = 0
    )
    val isDraggedState = pagerState.interactionSource.collectIsDraggedAsState()

    Box (
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(bottom = 20.dp)
    ) {

        HorizontalPager(
            pageSize = PageSize.Fill,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) { page ->
            ImageItem(item = items[page])
        }

        // Start auto-scroll effect
        LaunchedEffect(isDraggedState) {
            // convert compose state into flow
            snapshotFlow { isDraggedState.value }
                .collectLatest { isDragged ->
                    // if not isDragged start slide animation
                    if (!isDragged) {
                        // infinity loop
                        while (true) {
                            // duration before each scroll animation
                            delay(3_000L)
                            runCatching {
                                pagerState.animateScrollToPage(pagerState.currentPage.inc() % pagerState.pageCount)
                            }
                        }
                    }
                }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            repeat(pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.Red
                    else Color.White
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                ) {

                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Homepage()
}
