package pt.fabiomatos.connectedcinema.ui.navigation

interface Destinations {
    val route: String
}

object SplashScreen: Destinations {
    override val route = "SplashScreen"
}

object Login: Destinations {
    override val route = "Login"
}

object Homepage: Destinations {
    override val route = "Homepage"
}