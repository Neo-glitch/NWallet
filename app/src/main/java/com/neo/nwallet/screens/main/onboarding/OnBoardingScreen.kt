package com.neo.nwallet.screens.main.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.neo.nwallet.R
import com.neo.nwallet.model.OnBoardingItem
import com.neo.nwallet.navigation.NWalletScreens
import com.neo.nwallet.ui.theme.NWalletTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun OnBoardingScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: OnBoardingViewModel = hiltViewModel()
) {

    val onBoardingItems = OnBoardingItem.getOnBoardingItems()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    NWalletTheme {  // just put here for designing work purposes

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            HorizontalPager(
                count = onBoardingItems.size,
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically
            ) { page ->

                val item = onBoardingItems[page]
                PagerContent(item = item)
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 50.dp, vertical = 10.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                BottomContent(pagerState = pagerState) {
                    if (pagerState.currentPage != 2)
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    else{
                        scope.launch {
                            viewModel.setHasSeen(true)
                            navController.navigate(NWalletScreens.HomeScreen.name){
                                popUpTo(0)
                            }
                        }
                    }


                }

            }
        }
    }
}

@Composable
fun PagerContent(item: OnBoardingItem) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = item.image),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .background(MaterialTheme.colors.secondary.copy(0.05f)),
            contentDescription = "pager Image"
        )

        Text(
            text = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontSize = 30.sp,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )

        Text(
            text = item.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, start = 8.dp, end = 8.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily(Font(R.font.poppins_light))
        )

    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
inline fun BottomContent(pagerState: PagerState, crossinline onTextButtonClicked: () -> Unit) {
    HorizontalPagerIndicator(
        pagerState = pagerState,
        indicatorHeight = 8.dp,
        indicatorWidth = 8.dp,
        inactiveColor = MaterialTheme.colors.secondary.copy(0.3f),
        activeColor = MaterialTheme.colors.secondary,
    )

    Text(
        modifier = Modifier.clickable {onTextButtonClicked.invoke()},
        text = if (pagerState.currentPage != 2) "Next" else "Get Started",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = MaterialTheme.colors.secondary
    )
}

