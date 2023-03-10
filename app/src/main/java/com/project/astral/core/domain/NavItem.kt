package com.project.astral.core.domain

import com.project.astral.R

sealed class NavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : NavItem("Home", R.drawable.baseline_home_24, "home")
    object News: NavItem("News", R.drawable.baseline_newspaper_24, "news")
    object SpaceFlights: NavItem("Launch", R.drawable.baseline_rocket_24, "spaceflight")
    object SpaceFleet: NavItem("Fleet", R.drawable.baseline_fleet_24, "fleet")
    object Explore: NavItem("Explore", R.drawable.baseline_explore_24, "explore")
    object Settings: NavItem("Settings", R.drawable.baseline_settings_24, "settings")
}