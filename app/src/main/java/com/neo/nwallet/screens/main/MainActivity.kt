package com.neo.nwallet.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.neo.nwallet.navigation.NWalletNavigation
import com.neo.nwallet.navigation.NWalletScreens
import com.neo.nwallet.ui.theme.NWalletTheme
import com.neo.nwallet.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hasSeen = intent.getBooleanExtra(Constants.HAS_SEEN_ONBOARDING, false)
        setContent {
            NWalletTheme {
                NWalletApp(hasSeen)
            }
        }
    }
}

@Composable
fun NWalletApp(hasSeen: Boolean = false) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        if (hasSeen) {
            NWalletNavigation(startDestination = NWalletScreens.HomeScreen.name)
        } else {
            NWalletNavigation(startDestination = NWalletScreens.OnBoardingScreen.name)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NWalletTheme {
        NWalletApp()
    }
}