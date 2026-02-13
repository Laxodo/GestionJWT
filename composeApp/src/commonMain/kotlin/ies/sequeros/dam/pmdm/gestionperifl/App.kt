package ies.sequeros.dam.pmdm.gestionperifl

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import gestionjwt.composeapp.generated.resources.Res
import gestionjwt.composeapp.generated.resources.compose_multiplatform
import ies.sequeros.dam.pmdm.gestionperifl.ui.Access
import ies.sequeros.dam.pmdm.gestionperifl.ui.Main
import ies.sequeros.dam.pmdm.gestionperifl.ui.appsettings.AppViewModel
import ies.sequeros.dam.pmdm.gestionperifl.ui.login.LoginScreen
import org.koin.compose.viewmodel.koinViewModel

//expect fun getSettings(): Settings

@Composable
@Preview
fun App() {
    val appViewModel: AppViewModel = koinViewModel()
    AppTheme(appViewModel.isDarkMode.collectAsState()) {
        Access()
    }
}