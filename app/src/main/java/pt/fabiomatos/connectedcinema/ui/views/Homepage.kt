package pt.fabiomatos.connectedcinema.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.fabiomatos.connectedcinema.R
import pt.fabiomatos.connectedcinema.ui.navigation.DetailsScreen
import pt.fabiomatos.connectedcinema.ui.navigation.NavigationItem
import pt.fabiomatos.connectedcinema.ui.theme.ConnectedCinemaTheme
import pt.fabiomatos.connectedcinema.ui.views.screens.DetailsScreen
import pt.fabiomatos.connectedcinema.ui.views.screens.FavoritesScreen
import pt.fabiomatos.connectedcinema.ui.views.screens.HomeScreen
import pt.fabiomatos.connectedcinema.ui.views.screens.MoreScreen
import pt.fabiomatos.connectedcinema.ui.views.screens.SearchScreen
import pt.fabiomatos.connectedcinema.ui.views.screens.WhishlistScreen

@Composable
fun Homepage (){
    val navController: NavHostController = rememberNavController()
    val bottomBarState = remember { mutableStateOf(true) }

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorites,
        NavigationItem.Search,
        NavigationItem.Whishlist,
        NavigationItem.More
    )

    Surface(color = Color.Transparent) {
        Scaffold(
            bottomBar = {
                if (bottomBarState.value) {
                    BottomNavigationBar(items, navController)
                }
            },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(navController = navController)
                }

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    val found = items.any { item ->
                        destination.route == item.route
                    }
                    bottomBarState.value = found
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    Homepage()
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) { HomeScreen(navController) }
        composable(NavigationItem.Favorites.route) {FavoritesScreen() }
        composable(NavigationItem.Search.route) {SearchScreen() }
        composable(NavigationItem.Whishlist.route) { WhishlistScreen() }
        composable(NavigationItem.More.route) { MoreScreen() }
        composable(
            "${DetailsScreen.route}/{mediaType}/{id}",
            arguments = listOf(
                navArgument("mediaType") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val _mediaType = backStackEntry.arguments?.getString("mediaType")
            val _id = backStackEntry.arguments?.getInt("id")
            DetailsScreen(id = _id, mediaType = _mediaType, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(items: List<NavigationItem>, navController: NavController) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            ImageVector.vectorResource(id = item.icon),
                            tint =
                            if (index == selectedItemIndex) {
                                MaterialTheme.colorScheme.tertiary
                            } else
                                MaterialTheme.colorScheme.primary,
                            contentDescription = item.title
                        )
                    }
                },
                alwaysShowLabel = false,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                        selectedItemIndex = index
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.tertiary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    }
}

