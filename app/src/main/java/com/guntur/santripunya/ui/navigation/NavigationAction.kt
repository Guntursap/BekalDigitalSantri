package com.guntur.santripunya.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NavigationAction(navController: NavHostController){
    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
    val navigateToAbout: () -> Unit = {
        navController.navigate(Screen.About.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}