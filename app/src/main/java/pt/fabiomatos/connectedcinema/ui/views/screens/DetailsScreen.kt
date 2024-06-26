package pt.fabiomatos.connectedcinema.ui.views.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.viewmodel.MoviesViewModel

@Composable
fun DetailsScreen(movieId: String?, viewModel: MoviesViewModel = viewModel()) {

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
                Details(movieId)
            }
        }
    }
}

@Composable
fun Details(movieId: String?) {
    val movieName = movieId
    Log.d("DETAILS PAGE -> ", movieId.toString())
}

@Preview
@Composable
fun DetailsPreview() {
    Details("")
}