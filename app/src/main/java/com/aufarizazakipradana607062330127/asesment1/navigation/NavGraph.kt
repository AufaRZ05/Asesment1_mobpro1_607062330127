package com.aufarizazakipradana607062330127.asesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aufarizazakipradana607062330127.asesment1.ui.screen.AboutScreen
import com.aufarizazakipradana607062330127.asesment1.ui.screen.MainScreen

@Composable
fun SetupNavGraph (navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen()
        }
        composable(route = Screen.About.route) {
            AboutScreen()
        }
    }
}