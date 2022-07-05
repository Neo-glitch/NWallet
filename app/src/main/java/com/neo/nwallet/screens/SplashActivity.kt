package com.neo.nwallet.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.neo.nwallet.R
import com.neo.nwallet.screens.main.MainActivity
import com.neo.nwallet.ui.theme.NWalletTheme
import com.neo.nwallet.utils.Constants
import com.neo.nwallet.utils.PreferenceDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @Inject lateinit var preferenceDataStore: PreferenceDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            hideSystemUI()
            NWalletTheme {
                // A surface container using the 'background' color from the theme

                SplashScreen(preferenceDataStore){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(Constants.HAS_SEEN_ONBOARDING, it)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }


    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R){
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else{
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }


}


@Composable
inline fun SplashScreen(preferenceDataStore: PreferenceDataStore, crossinline goToMainActivity: (Boolean) -> Unit) {
    val state = remember { MutableTransitionState(false) }
    val hasSeen = preferenceDataStore.getHasSeenOnboarding.collectAsState(initial = false)

    LaunchedEffect(true) {
        delay(500L)
        state.targetState = true
        delay(3000L)

        goToMainActivity.invoke(hasSeen.value)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colorStops = arrayOf(
                            Pair(0.4f, MaterialTheme.colors.primary),
                            Pair(1.0f, MaterialTheme.colors.secondary)
                        )
                    )
                )
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                AnimatedVisibility(
                    visibleState = state,
                    enter = fadeIn(
                        initialAlpha = 0f,
                        animationSpec = tween(durationMillis = 2500)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_bitcoin),
                            modifier = Modifier.size(50.dp),
                            contentDescription = "splash icon"
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "Nwallet",
                            fontSize = 40.sp,
                            color = Color.DarkGray,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            letterSpacing = 3.sp
                        )
                    }
                }

            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NWalletTheme {
//        SplashScreen() { }

    }
}
