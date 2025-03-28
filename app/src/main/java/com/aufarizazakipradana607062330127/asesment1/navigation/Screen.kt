package com.aufarizazakipradana607062330127.asesment1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}