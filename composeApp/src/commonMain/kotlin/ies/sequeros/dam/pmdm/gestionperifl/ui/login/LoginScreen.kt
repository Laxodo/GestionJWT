package ies.sequeros.dam.pmdm.gestionperifl.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.LaunchedEffect
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.login.LoginComponent

@Composable
fun LoginScreen(
    onCancel: () -> Unit,
) {
    val viewModel = koinViewModel<LoginFormViewModel>()
    //estado del formulario que es el del LoginComponent
    val state by viewModel.state.collectAsState()

    LoginComponent(state,viewModel::onEmailChange,viewModel::onPasswordChange,
        {
            viewModel.login()
        },
        {
            onCancel()
        })
}