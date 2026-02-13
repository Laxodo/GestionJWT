package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.cambiarContraseña

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordUseCase
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterCommand
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.changePassword.ChangePasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePasswordFormViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ChangePasswordState())
    val state: StateFlow<ChangePasswordState> = _state

    fun onCurrentPasswordChange(password: String) {
        _state.update { it.copy(
            password = password,
            isValid = password.isNotBlank() && it.newPassword.isNotBlank()
            )
        }
    }

    fun onNewPasswordChange(password: String) {
        _state.update { it.copy(
            newPassword = password,
            isValid = it.password.isNotBlank() && password.isNotBlank()
            )
        }
    }

    fun changePassword() {
        viewModelScope.launch {
            val current = state.value.password
            val new = state.value.newPassword
            if (current.isBlank() || new.isBlank()) {
                _state.update { it.copy(errorMessage = "Los campos no pueden estar vacíos") }
                return@launch
            }
            if (new.length < 6) {
                _state.update { it.copy(newPasswordError = "La contraseña debe tener al menos 6 caracteres") }
                return@launch
            }
            if (current == new) {
                _state.update { it.copy(newPasswordError = "La nueva contraseña no puede ser igual a la actual") }
                return@launch
            }

            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                _state.value = state.value.copy(isLoading = true)
                val changePasswordCommand = ChangePasswordCommand(
                    oldPassword =  state.value.password,
                    newPassword = state.value.newPassword
                )
                val result = changePasswordUseCase(changePasswordCommand).onSuccess {
                    _state.update { it.copy(isLoading = false, isChangePasswordSuccess = true) }
                }.onFailure { e ->
                    _state.update { it.copy(isLoading = false, isChangePasswordSuccess = false) }
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