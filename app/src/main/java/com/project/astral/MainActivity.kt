package com.project.astral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.astral.core.domain.NavItem
import com.project.astral.core.utils.regularFont
import com.project.astral.features.NewsScreen
import com.project.astral.features.explore.ExploreScreen
import com.project.astral.features.fleet.FleetScreen
import com.project.astral.features.home.HomeScreen
import com.project.astral.features.launch.LaunchScreen
import com.project.astral.features.settings.SettingsScreen
import com.project.astral.ui.theme.AstralTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstralTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}

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
                label = { com.project.astral.core.components.Text(text = item.title, font = regularFont()) },
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
            LaunchScreen()
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AstralTheme {
        MainScreenView()
    }
}