package ies.sequeros.dam.pmdm.gestionperifl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.dam.pmdm.gestionperifl.Routes
import ies.sequeros.dam.pmdm.gestionperifl.ui.login.LoginScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Access() {
    val mainViewModel: MainViewModel = koinViewModel()
    val navController = rememberNavController()

    val user by mainViewModel.currentUserState.collectAsState()

    val navegador: @Composable () -> Unit = {
        NavHost(
            navController = navController,
            startDestination = Routes.ACCESS
        ){
            composable(Routes.ACCESS) {
                AccessScreen(
                    {
                        navController.navigate(Routes.LOGIN){
                            launchSingleTop = true
                        }
                    },
                    {
                        navController.navigate(Routes.LOGIN){
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.MAINAPP) {
                Main()
            }
            composable(Routes.LOGIN) {
                LoginScreen(
                    {}
                )
            }
        }
    }

    Scaffold() { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            navegador()
            if (user != null){
                navController.navigate(Routes.MAINAPP) {
                    launchSingleTop = true
                }
            }else{
                navController.navigate(Routes.ACCESS) {
                    launchSingleTop = true
                }
            }
        }
    }

}