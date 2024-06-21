package pt.fabiomatos.connectedcinema.ui.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.models.Results
import pt.fabiomatos.connectedcinema.ui.navigation.Login
import pt.fabiomatos.connectedcinema.ui.navigation.WelcomeItem
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.utils.Utils
import pt.fabiomatos.connectedcinema.viewmodel.MoviesViewModel

@Composable
fun HomeScreen(viewModel: MoviesViewModel = viewModel()) {

    val trending by viewModel.trending.observeAsState(initial = emptyList())
    val upcoming by viewModel.upcoming.observeAsState(initial = emptyList())

    ConnectedCinemaTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TrendingList(trending.take(n = 5))
                UpcomingList(upcoming)
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
fun UpcomingList(items: List<Results>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = "Upcoming",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp, bottom = 5.dp)
                .background(Color.Transparent), // Background color to show the shadow clearly
        )
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
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
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
            Text(
                text = item.title ?: item.name.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 80.dp)
                    .background(Color.Transparent), // Background color to show the shadow clearly
            )

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(items: List<Results>) {

    val pageCount = items.size
    val pagerState = rememberPagerState(pageCount = { pageCount })

    Box (
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(bottom = 20.dp)
    ) {

        HorizontalPager(
            state = pagerState
        ) { page ->
            ImageItem(item = items[page])
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



//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen()
//}
