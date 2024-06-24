package pt.fabiomatos.connectedcinema.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.ui.navigation.WelcomeItem
import java.time.LocalDate

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertStringDateToYear (data: String): String {
        val localDate = LocalDate.parse(data) // Using default format

        return localDate.year.toString()
    }

    @SuppressLint("DefaultLocale")
    fun getRatingFromAverage (rating: Double?): String {
        return String.format("%.1f", rating)
    }
}