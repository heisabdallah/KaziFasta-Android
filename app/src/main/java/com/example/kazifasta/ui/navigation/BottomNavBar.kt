package com.example.kazifasta.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kazifasta.database
import com.example.kazifasta.ui.screens.category.CategoryScreen
import com.example.kazifasta.ui.screens.favorites.FavoritesScreen
import com.example.kazifasta.ui.screens.home.HomeScreen
import com.example.kazifasta.ui.screens.profile.UserProfileScreen
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateBlack
import com.example.kazifasta.ui.theme.mateWhite

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()

    val navItems = listOf(
        BottomNavItem("Home", inActiveIcon = Icons.Outlined.Home, activeIcon = Icons.Filled.Home, label = "Home"),
        BottomNavItem("Category", inActiveIcon = Icons.Outlined.DateRange, activeIcon = Icons.Filled.DateRange, label = "Category"),
        BottomNavItem("Favorites", inActiveIcon = Icons.Outlined.FavoriteBorder, activeIcon = Icons.Filled.Favorite, label = "Favorites"),
        BottomNavItem("UserProfile", inActiveIcon = Icons.Outlined.Person, activeIcon = Icons.Filled.Person, label = "Profile")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxHeight(0.09f), containerColor = mateWhite) {
                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true

                            }
                        },
                        icon = { Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            if (currentRoute == item.route) Icon(item.activeIcon, contentDescription = null) else Icon(item.inActiveIcon, contentDescription = null)
                            Text(text = item.label, fontSize = 11.sp, fontWeight = if (currentRoute == item.route) FontWeight.Medium else FontWeight.Light, color = if (currentRoute == item.route) green else mateBlack)
                        }  },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = green,
                            indicatorColor = mateWhite
                        ),
//                        label = { Text(text = item.label, color = if (currentRoute == item.route) green else mateBlack) }
                    )
                }
            }
        },
        content = {it
            NavHost(
                navController = navController,
                startDestination = "Home",
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ) {
                composable("Home") { HomeScreen(navController) }
                composable("Category") { CategoryScreen() }
                composable("Favorites") { FavoritesScreen() }
                composable("UserProfile") { UserProfileScreen(profile = database.profilesList.last()) }
            }
        }
    )
}

data class BottomNavItem(val route: String, val inActiveIcon: ImageVector, val activeIcon: ImageVector, val label: String)


