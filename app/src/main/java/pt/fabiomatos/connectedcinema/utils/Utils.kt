package pt.fabiomatos.connectedcinema.utils

import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.ui.navigation.WelcomeItem

object Utils {
    val welcomeImages = listOf(
        WelcomeItem(
            0,
            "Please note that you can´t watch, stream or download shows and movies with",
            R.drawable.step0),
        WelcomeItem(
            1,
            "Here you can see what is the trending shows and movies and other informations",
            R.drawable.step1),
        WelcomeItem(
            2,
            "You can search and navigate through the catalog of movies and tv shows ",
            R.drawable.step2),
        WelcomeItem(
            3,
            "You can add your favorites movies and tv shows and add other to your whislist",
            R.drawable.step3)
    )
}