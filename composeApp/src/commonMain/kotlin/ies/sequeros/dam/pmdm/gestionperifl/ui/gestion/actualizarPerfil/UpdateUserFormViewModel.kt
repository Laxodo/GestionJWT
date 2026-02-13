package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.actualizarPerfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.update.UpdateUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.update.UpdateUserUseCase
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.updateUser.UpdateUserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdateUserFormViewModel(
    val updateUserUseCase: UpdateUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(UpdateUserState())
    val state: StateFlow<UpdateUserState> = _state.asStateFlow()
    val isFormValid = MutableStateFlow(false)

    fun onUsernameChange(username: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    newUsername = username,
                    newUsernameError = when {
                        username.isBlank() -> "El nombre no puede estar vacío"
                        username.length >= 6 -> null
                        else -> "Mínimo 6 caracteres"
                    }
                )
            }
            validateForm()
        }
    }

    private fun validateForm() {
        val s = _state.value
        isFormValid.value =
            s.newUsername.isNotBlank() &&
            s.newUsernameError == null
        _state.value=state.value.copy( isValid = isFormValid.value)
    }

    fun updateUser() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                _state.value = state.value.copy(isLoading = true)
                val updateUserCommand = UpdateUserCommand(
                    name = state.value.newUsername,
                    estado = state.value.newStatus
                )
                val result = updateUserUseCase(updateUserCommand).onSuccess{
                    _state.update { it.copy(isLoading = false, isUpdateUserSuccess = true) }
                }.onFailure {
                    _state.update { it.copy(isLoading = false, isUpdateUserSuccess = false) }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error al conectar: ${e.message}"
                    )
                }
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}