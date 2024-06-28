package pt.fabiomatos.connectedcinema.ui.views.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import pt.fabiomatos.connectedcinema.enums.MediaType
import pt.fabiomatos.connectedcinema.models.Movie
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.utils.Utils
import pt.fabiomatos.connectedcinema.viewmodel.HomeViewModel
import pt.fabiomatos.connectedcinema.viewmodel.MoviesViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import pt.fabiomatos.connectedcinema.R

@Composable
fun DetailsScreen(
    id: Int?,
    mediaType: String?,
    navController: NavHostController,
    viewModel: MoviesViewModel = viewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    ConnectedCinemaTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (
                modifier = Modifier
                    .verticalScroll(state = scrollState)
            ) {
                Log.e("ENTROU DetailsScreen -> ", "DetailsScreen")
                val _mediaType = MediaType.fromMediaType(mediaType!!)
                when (_mediaType) {
                    MediaType.MOVIE -> {
                        Log.e("ENTROU MOVIE -> ", "DetailsScreen")
                        DetailsMovie(id, viewModel, navController)
                    }
                    MediaType.TV -> {
                        Log.e("ENTROU TV -> ", "DetailsTv")
                        DetailsTv(id)
                    }
                    MediaType.PEOPLE -> {
                        Log.e("ENTROU PEOPLE -> ", "DetailsPeope")
                        DetailsPeople(id)
                    }
                    else -> {
                        Log.e("ENTROU else -> ", "DetailsScreen")
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }

            }
        }
    }
}

@Composable
fun DetailsMovie(
    movieId: Int?, viewModel: MoviesViewModel,
    navController: NavHostController) {

    Log.e("ENTROU DETALHES -> ", "$movieId")

    val movie by viewModel.details.observeAsState()
    var isLoading by remember { mutableStateOf(true) }

    viewModel.fetchMovie(movieId!!)

    LaunchedEffect(movie) {
        isLoading = false
        Log.e("ENTROU LaunchedEffect -> ", "$movieId")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            movie?.let { TopScreen(it, navController) }
        }
    }
}



@Composable
fun DetailsTv(tvId: Int?) {

    Log.d("DETAILS PAGE -> ", tvId.toString())
}

@Composable
fun DetailsPeople(peopleId: Int?) {

    Log.d("DETAILS PAGE -> ", peopleId.toString())
}

@Composable
fun TopScreen(item: Movie, navController: NavHostController) {
    Box {
        Image(
//            painter = rememberImagePainter(
//                data = "https://image.tmdb.org/t/p/w500${item.backdropPath}"
//            ),
            painter = painterResource(id = R.drawable.cover),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(400.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.secondary.copy(alpha = 1f)
                        ),
                        startY = 0.5f, // Start gradient at the middle
                        endY = 750.dp.value // End gradient at the bottom
                    )
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(bottom = 100.dp)
            ) {
                Text(
                    text = item.title ?: item.originalTitle.toString(),
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

                    Text(
                        text = Utils.convertStringDateToYear(item.releaseDate!!),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .background(Color.Transparent), // Background color to show the shadow clearly
                    )

                    Icon(
                        Icons.Filled.Star,
                        tint = MaterialTheme.colorScheme.tertiary,
                        contentDescription = "",
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailsPreview() {
    val navController: NavHostController = rememberNavController()
    TopScreen(
        Movie(title = "TESTE", releaseDate = "2014-01-10", voteAverage = 7.896),
        navController)
}