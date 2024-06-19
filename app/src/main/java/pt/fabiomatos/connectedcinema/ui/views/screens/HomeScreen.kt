package pt.fabiomatos.connectedcinema.ui.views.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.fabiomatos.connectedcinema.ui.navigation.WelcomeItem
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.viewmodel.MoviesViewModel

@Composable
fun HomeScreen(viewModel: MoviesViewModel = viewModel()) {
//    val images = Utils.welcomeImages
//    ConnectedCinemaTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            ImageSlider(images = images)
//        }
//    }
    ConnectedCinemaTheme {
        val upcoming by viewModel.upcoming.observeAsState(initial = emptyList())

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(upcoming) { movie ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = movie.id.toString())
                    Text(text = movie.title.toString())
                }
            }
        }
    }
}

@Composable
fun ImageItem(imageRes: WelcomeItem){
    Column {
        Image(
            painter = painterResource(id = imageRes.icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .size(350.dp)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<WelcomeItem>) {

    val pageCount = images.size
    val pagerState = rememberPagerState(pageCount = { pageCount })

//    BoxWithConstraints(modifier = Modifier.fillMaxWidth(1f)) {
//
//    }


    HorizontalPager(
        state = pagerState
    ) { page ->
        ImageItem(imageRes = images[page])
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color.Red
                else MaterialTheme.colorScheme.primary
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
