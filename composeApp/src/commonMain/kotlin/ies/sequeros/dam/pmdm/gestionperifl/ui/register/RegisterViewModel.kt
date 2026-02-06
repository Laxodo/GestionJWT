package ies.sequeros.dam.pmdm.gestionperifl.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.user.register.RegisterUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.user.register.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()
    val isFormValid = MutableStateFlow(false)

    fun onChangeUsername(v: String) {
        _uiState.value = _uiState.value.copy(
            username = v,
            usernameError = null,
        )
    }

    fun onChangePassword(v: String) {
        _uiState.value = _uiState.value.copy(
            password = v,
            passwordError = null
        )
    }

    fun onChangeVerifyPassword(v: String) {
        _uiState.value = _uiState.value.copy(
            verifyPassword = v,
            passwordError = null
        )
    }

    fun onChangeEmail(v: String) {
        _uiState.value = _uiState.value.copy(
            email = v,
            emailError = if (v.contains("@")) null else "Email no válido"
        )

    }
    private fun validateForm() {
        val s = _uiState.value
        isFormValid.value = s.email.isNotBlank() &&
                s.password.isNotBlank() &&
                s.emailError == null &&
                s.passwordError == null
        _uiState.value=uiState.value.copy( isValid = isFormValid.value)
    }

    fun register() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                //cargando
                _uiState.value = uiState.value.copy(isLoading = true)
                //crear el comando, llamar al caso de uso
                //que devuelve ok, o un error en el result
                val registerUserCommand =
                    RegisterUserCommand(
                        username = _uiState.value.username,
                        password = uiState.value.password,
                        email = uiState.value.email,
                    )
                /*
                val result = registerUserUseCase(registerUserCommand).onSuccess{
                    //_state.value = _state.value.copy(isLoginSuccess = true)
                    _uiState.update { it.copy(isLoading = false, isLoginSuccess = true) }
                }.onFailure {
                    _uiState.update { it.copy(isLoading = false, isLoginSuccess = false) }
                    //meter aqui el error

                }
                */


            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error al conectar: ${e.message}"
                    )
                }
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

}