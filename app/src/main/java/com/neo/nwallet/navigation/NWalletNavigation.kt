package com.neo.nwallet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neo.nwallet.screens.main.home.HomeScreen
import com.neo.nwallet.screens.main.onboarding.OnBoardingScreen

@Composable
fun NWalletNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){

        composable(route = NWalletScreens.OnBoardingScreen.name){
            OnBoardingScreen(navController = navController)
        }

        composable(route = NWalletScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
    }
}