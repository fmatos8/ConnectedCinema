package pt.fabiomatos.connectedcinema.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.fabiomatos.connectedcinema.ui.navigation.Homepage
import pt.fabiomatos.connectedcinema.ui.navigation.Login
import pt.fabiomatos.connectedcinema.ui.navigation.SplashScreen
import pt.fabiomatos.connectedcinema.ui.navigation.Welcome
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.ui.views.Homepage
import pt.fabiomatos.connectedcinema.ui.views.LoginScreen
import pt.fabiomatos.connectedcinema.ui.views.SplashScreen
import pt.fabiomatos.connectedcinema.ui.views.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ConnectedCinemaTheme {
                MyNavigation()
            }
        }
    }
}

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashScreen.route) {
        composable(SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Welcome.route){
            WelcomeScreen(navController)
        }
        composable(Login.route){
            LoginScreen(navController)
        }
        composable(Homepage.route){
            Homepage()
        }
    }
}