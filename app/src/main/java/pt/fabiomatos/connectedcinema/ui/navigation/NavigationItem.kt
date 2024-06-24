package pt.fabiomatos.connectedcinema.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import pt.fabiomatos.connectedcinema.R

sealed class NavigationItem(
    val title: String,
    var route: String,
    val icon: Int,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
) {
    data object Home : NavigationItem(
        title = "",
        route = "home",
        icon = R.drawable.ic_bottom_home
    )
    data object Search : NavigationItem(
        title = "",
        route = "search",
        icon = R.drawable.ic_bottom_search
    )
    data object Favorites : NavigationItem(
        title = "",
        route = "favorites",
        icon = R.drawable.ic_bottom_favorites,
        hasNews = true
    )
    data object Whishlist : NavigationItem(
        title = "",
        route = "whishlist",
        icon = R.drawable.ic_bottom_whishlist,
        hasNews = true
    )
    data object More : NavigationItem(
        title = "",
        route = "more",
        icon = R.drawable.ic_bottom_more
    )
}