package com.example.tic_tac_toe_multiplayer

sealed class Screen(val route:String) {
    data object HomeScreen:Screen("home_screen")
    data object OfflineScreen:Screen("offline_screen")
}