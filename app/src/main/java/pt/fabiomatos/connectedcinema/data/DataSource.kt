package pt.fabiomatos.connectedcinema.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import pt.fabiomatos.connectedcinema.R


object DataSource {
    val loginOptions = listOf(
        Pair("Google", R.drawable.button_1),
        Pair("Facebook", R.drawable.button_2),
        Pair("Apple", R.drawable.button_3)
    )


}

@Composable
fun dataBottomMenu(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Home,
            selectedIconColor = MaterialTheme.colorScheme.tertiary,
            unselectedIcon = Icons.Outlined.Home,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Email,
            selectedIconColor = MaterialTheme.colorScheme.tertiary,
            unselectedIcon = Icons.Outlined.Email,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Settings,
            selectedIconColor = MaterialTheme.colorScheme.tertiary,
            unselectedIcon = Icons.Outlined.Settings,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Settings,
            selectedIconColor = MaterialTheme.colorScheme.tertiary,
            unselectedIcon = Icons.Outlined.Settings,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Settings,
            selectedIconColor = MaterialTheme.colorScheme.tertiary,
            unselectedIcon = Icons.Outlined.Settings,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            hasNews = true,
        )
    )
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val selectedIconColor: Color,
    val unselectedIcon: ImageVector,
    val unselectedIconColor: Color,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)