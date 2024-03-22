package pt.fabiomatos.connectedcinema

interface Destinations {
    val route: String
}

object Login:Destinations {
    override val route = "Login"
}

object Home:Destinations {
    override val route = "Home"
}