package ies.sequeros.dam.pmdm.gestionperifl.ui.register

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.register.RegisterComponent
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun RegisterScreen(
    onRegister: () -> Unit,
    onCancel: () -> Unit,
) {
    val viewModel = koinViewModel<RegisterFromViewModel>()
    // estado del formulario
    val state by viewModel.state.collectAsState()
    // estado correcto, revisa el padre
    LaunchedEffect(state.isRegisterSuccess) {
        if(state.isRegisterSuccess) {
            onRegister()
        }
    }

    RegisterComponent(
        state,
        {
            viewModel.register()
        }, {
            onCancel()
        })
}