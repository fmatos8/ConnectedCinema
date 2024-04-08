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