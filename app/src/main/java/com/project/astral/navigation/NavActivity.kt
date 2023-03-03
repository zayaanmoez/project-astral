package com.project.astral.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.project.astral.core.components.Text
import com.project.astral.core.utils.regularFont

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        NavItem.Home,
        NavItem.News,
        NavItem.SpaceFlights,
        NavItem.SpaceFleet,
        NavItem.Explore,
    )
    NavigationBar() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title, font = regularFont()) },
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavItem.Home.screen_route) {
        composable(NavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(NavItem.News.screen_route) {
            NewsScreen()
        }
        composable(NavItem.SpaceFlights.screen_route) {
            SpaceFlightScreen()
        }
        composable(NavItem.SpaceFleet.screen_route) {
            FleetScreen()
        }
        composable(NavItem.Explore.screen_route) {
            ExploreScreen()
        }
        composable(NavItem.Settings.screen_route) {
            SettingsScreen()
        }
    }
}