package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.actualizarPerfil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.updateUser.UpdateUserComponent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UpdateUserScreen(
    onUpdateUser: () -> Unit,
    onCancel: () -> Unit
) {
    val viewModel = koinViewModel<UpdateUserFormViewModel>()
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.isUpdateUserSuccess) {
        if(state.isUpdateUserSuccess) {
            onUpdateUser()
        }
    }

    UpdateUserComponent(
        state,
        viewModel::onUsernameChange,
        {
            viewModel.updateUser()
        }, {
            onCancel()
        }
    )
}