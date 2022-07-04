package com.neo.nwallet.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.neo.nwallet.navigation.NWalletNavigation
import com.neo.nwallet.navigation.NWalletScreens
import com.neo.nwallet.ui.theme.NWalletTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NWalletTheme {
                // A surface container using the 'background' color from the theme
                NWalletApp()
            }
        }
    }
}

@Composable
fun NWalletApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
//        val hasSeen = true

        NWalletNavigation(startDestination = NWalletScreens.OnBoardingScreen.name)
    }

}




@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NWalletTheme {
        NWalletApp()
    }
}