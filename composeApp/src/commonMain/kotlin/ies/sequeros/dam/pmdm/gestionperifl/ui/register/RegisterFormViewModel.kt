package ies.sequeros.dam.pmdm.gestionperifl.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterUseCase
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.register.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterFormViewModel(
    val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()
    val isFormValid = MutableStateFlow(false)

    fun onUsernameChange(username: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    username = username,
                    usernameError = when {
                        username.isBlank() -> "El nombre no puede estar vacío"
                        else -> null
                    }
                )
            }
            validateForm()
        }
    }


    fun onEmailChange(email: String) {
        _state.update {
            it.copy(
                email = email,
                emailError = if (email.contains("@")) null else "Email no válido"
            )
        }
        validateForm()
    }

    fun onPasswordChange(password: String) {
        _state.update {
            it.copy(
                password = password,
                passwordError = if (password.length >= 6) null else "Mínimo 6 caracteres"
            )
        }
        validateForm()
    }

    fun onConfirmPassword(confirmPassword: String) {
        _state.update { currentState ->
            currentState.copy(
                verifyPassword = confirmPassword,
                verifyPasswordError = when {
                    confirmPassword != currentState.password -> "Las contraseñas no coinciden"
                    else -> null
                }
            )
        }
        validateForm()
    }


    private fun validateForm() {
        val s = _state.value
        isFormValid.value =
            s.username.isNotBlank() &&
            s.email.isNotBlank() &&
            s.password.isNotBlank() &&
            s.verifyPassword.isNotBlank() &&
            s.emailError == null &&
            s.passwordError == null &&
            s.verifyPasswordError == null
        _state.value=state.value.copy( isValid = isFormValid.value)
    }


    fun register() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                _state.value = state.value.copy(isLoading = true)
                val registerCommand = RegisterCommand(
                    username = state.value.username,
                    email = state.value.email,
                    password = state.value.password
                )
                val result = registerUseCase(registerCommand).onSuccess{
                    _state.update { it.copy(isLoading = false, isRegisterSuccess = true) }
                }.onFailure {
                    _state.update { it.copy(isLoading = false, isRegisterSuccess = false) }
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