package com.dm.berxley.stylishstore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.stylishstore.domain.sharedprefs.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(localUserManager: LocalUserManager) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var selectedBottomIndex by mutableStateOf(0)
        private set

    var startDestination by mutableStateOf(Screen.OnboardingNavigator.route)
        private set

    init {
        viewModelScope.launch {
            localUserManager.readIsLoggedIn().collectLatest { isLoggedIn ->

                if (isLoggedIn){
                    //set start navigator to app home
                    startDestination = Screen.MainNavigator.route
                }else{
                    //set start navigator to onboarding
                    startDestination = Screen.OnboardingNavigator.route
                }

                delay(3000)
                splashCondition = false
            }
        }
    }

}