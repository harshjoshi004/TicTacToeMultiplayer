package com.example.tic_tac_toe_multiplayer

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tic_tac_toe_multiplayer.models.OfflineViewModel
import kotlinx.parcelize.Parcelize

@Composable
fun Navigation(offlineViewModel: OfflineViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route = Screen.HomeScreen.route){
            HomeScreen { parcel->
                navController.currentBackStackEntry?.savedStateHandle?.set("parcel",parcel)
                navController.navigate(Screen.OfflineScreen.route)
            }
        }
        composable(route = Screen.OfflineScreen.route){
            val parcel = navController.previousBackStackEntry?.savedStateHandle?.get<MyParcel>("parcel")?: MyParcel("")
            OfflineScreen(viewModel = offlineViewModel, parcel = parcel) { navController.navigate(Screen.HomeScreen.route) }
        }
    }
}

@Parcelize
data class MyParcel(
    val name: String? = ""
):Parcelable