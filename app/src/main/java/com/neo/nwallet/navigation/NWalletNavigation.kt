package com.neo.nwallet.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neo.nwallet.screens.main.home.HomeScreen
import com.neo.nwallet.screens.main.home.HomeViewModel
import com.neo.nwallet.screens.main.onboarding.OnBoardingScreen
import com.neo.nwallet.screens.main.onboarding.OnBoardingViewModel

@Composable
fun NWalletNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){

        composable(route = NWalletScreens.OnBoardingScreen.name){
            val viewModel = hiltViewModel<OnBoardingViewModel>()
            OnBoardingScreen(navController = navController, viewModel)
        }

        composable(route = NWalletScreens.HomeScreen.name){
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController = navController, viewModel)
        }
    }
}