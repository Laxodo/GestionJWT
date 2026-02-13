package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.cambiarContraseña

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordUseCase
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.changePassword.ChangePasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePasswordFormViewModel(
    val passwordUseCase: ChangePasswordUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ChangePasswordState())
    val state: StateFlow<ChangePasswordState> = _state.asStateFlow()
    val isFormValid = MutableStateFlow(false)

    fun onPasswordChange(newPassword: String) {
        _state.update {
            it.copy(
                newPassword = newPassword,
                newPasswordError = if (newPassword.length >= 6) null else "Mínimo 6 caracteres"
            )
        }
        validateForm()
    }

    private fun validateForm() {
        val s = _state.value
        isFormValid.value =
            s.password.isNotBlank() &&
            s.newPassword.isNotBlank() &&
            s.passwordError == null &&
            s.newPasswordError == null
        _state.value=state.value.copy( isValid = isFormValid.value)
    }

    fun changePassword() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try{
                _state.value = state.value.copy(isLoading = true)
                val changePasswordCommand = ChangePasswordCommand(
                    oldPassword = state.value.password,
                    newPassword = state.value.newPassword
                )
                val result = passwordUseCase(changePasswordCommand).onSuccess{
                    _state.update { it.copy(isLoading = false, isChangePasswordSuccess = true) }
                }.onFailure {
                    _state.update { it.copy(isLoading = false, isChangePasswordSuccess = false) }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error al conectar: {e.message}"
                    )
                }
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}