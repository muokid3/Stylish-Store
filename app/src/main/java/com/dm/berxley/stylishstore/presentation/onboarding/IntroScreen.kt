package com.dm.berxley.stylishstore.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.stylishstore.Screen
import com.dm.berxley.stylishstore.presentation.onboarding.components.OffersIndicator
import com.dm.berxley.stylishstore.presentation.onboarding.components.OnboardingPage
import com.dm.berxley.stylishstore.presentation.onboarding.components.pages
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> {
                        listOf("", "Next")
                    }

                    1 -> {
                        listOf("Back", "Next")
                    }

                    2 -> {
                        listOf("Back", "Get Started")
                    }

                    else -> {
                        listOf("", "")
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "${pagerState.currentPage + 1}/${pagerState.pageCount}")
            TextButton(onClick = {
                //skip and go to login
                navController.navigate(Screen.LoginScreen.route)
            }) {
                Text(text = "SKIP >>")
            }

        }
        HorizontalPager(modifier = Modifier.fillMaxHeight(0.8f), state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (buttonState.value[0].isNotEmpty()) {
                TextButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                    Text(
                        text = buttonState.value[0],
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            } else {
                Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            }

            OffersIndicator(
                pageSize = pagerState.pageCount,
                selectedPage = pagerState.currentPage
            )

            Button(onClick = {
                scope.launch {
                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        //go to login
                        navController.navigate(Screen.LoginScreen.route)
                    } else {
                        //go to next pager
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }) {
                Text(
                    text = buttonState.value[1],
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun IntroPrev() {
    IntroScreen(navController = rememberNavController())
}