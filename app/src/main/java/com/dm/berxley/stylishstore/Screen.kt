package com.dm.berxley.stylishstore

sealed class Screen(val route: String) {

    object OnboardingNavigator: Screen(route = "onboardingNavigator")
    object AppIntroScreen: Screen(route = "appIntro")
    object LoginScreen: Screen(route = "loginScreen")
    object RegisterScreen: Screen(route = "registerScreen")
    object ForgotPasswordScreen: Screen(route = "forgotPasswordScreen")



    object MainNavigator: Screen(route = "mainNavigator")
    object  HomeScreen: Screen(route = "homeScreen")
    object  WishListScreen: Screen(route = "wishListScreen")
    object  CartScreen: Screen(route = "cartScreen")
    object  SearchScreen: Screen(route = "searchScreen")
    object  SettingsScreen: Screen(route = "settingsScreen")
}