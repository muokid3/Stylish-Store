package com.dm.berxley.stylishstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.stylishstore.presentation.onboarding.IntroScreen
import com.dm.berxley.stylishstore.ui.theme.StylishStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashCondition
            }
        }

        setContent {
            val navController = rememberNavController()

            StylishStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        bottomBar = {
                            if (mainViewModel.startDestination == Screen.MainNavigator.route) {
                                //show bottom bar
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = mainViewModel.startDestination,
                            modifier = Modifier.padding(paddingValues)
                        ) {

                            //onboarding
                            navigation(
                                route = Screen.OnboardingNavigator.route,
                                startDestination = Screen.AppIntroScreen.route
                            ) {
                                composable(route=Screen.AppIntroScreen.route){
                                    IntroScreen(navController = navController)
                                }
                                composable(route=Screen.LoginScreen.route){

                                }
                                composable(route=Screen.RegisterScreen.route){

                                }

                            }


                            //main app
                            navigation(
                                route = Screen.MainNavigator.route,
                                startDestination = Screen.HomeScreen.route
                            ) {
                                composable(route=Screen.HomeScreen.route){

                                }
                                composable(route=Screen.CartScreen.route){

                                }
                                composable(route=Screen.WishListScreen.route){

                                }

                                composable(route=Screen.SearchScreen.route){

                                }

                                composable(route=Screen.SettingsScreen.route){

                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StylishStoreTheme {
        Greeting("Android")
    }
}