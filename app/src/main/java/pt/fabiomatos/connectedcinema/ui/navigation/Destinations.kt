package pt.fabiomatos.connectedcinema.ui.navigation

import pt.fabiomatos.connectedcinema.interfaces.IDestinations

object SplashScreen: IDestinations {
    override val route = "SplashScreen"
}

object Welcome: IDestinations {
    override val route = "Welcome"
}

object Login: IDestinations {
    override val route = "Login"
}

object Homepage: IDestinations {
    override val route = "Homepage"
}
