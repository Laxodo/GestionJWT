package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.cambiarContraseña

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.changePassword.ChangePasswordComponent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun changePasswordScreen(
    onChangePassword: () -> Unit,
    onCancel: () -> Unit
) {
    val viewModel = koinViewModel<ChangePasswordFormViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isChangePasswordSuccess) {
        if (state.isChangePasswordSuccess) {
            onChangePassword()
        }
    }

    ChangePasswordComponent(state,
        viewModel::onCurrentPasswordChange,
        viewModel::onNewPasswordChange,
        {
            viewModel.changePassword()
        }, {
            onCancel()
        }
    )
}
