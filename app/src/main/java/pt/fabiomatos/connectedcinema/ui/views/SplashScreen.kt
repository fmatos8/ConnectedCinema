package pt.fabiomatos.connectedcinema.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.ui.navigation.Login
import pt.fabiomatos.connectedcinema.ui.navigation.SplashScreen
import pt.fabiomatos.connectedcinema.ui.navigation.Welcome

@Composable
fun SplashScreen(
    navController: NavHostController = rememberNavController()
) {
    LaunchedEffect(key1 = true, block = {
        delay(3000)
        navController.navigate(Welcome.route) {
            popUpTo(SplashScreen.route) {
                inclusive = true
                saveState = true
            }
        }
    })
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color =  MaterialTheme.colorScheme.background
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, end = 40.dp),
                painter = painterResource(id = R.drawable.logo_transparent),
                contentDescription = "Logo"
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenComposePreview(){
    SplashScreen()
}