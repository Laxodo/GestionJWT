package ies.sequeros.dam.pmdm.gestionperifl.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.application.register.RegisterCommand
import ies.sequeros.dam.pmdm.gestionperifl.application.register.RegisterUseCase
import ies.sequeros.dam.pmdm.gestionperifl.ui.components.register.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterFromViewModel(
    val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

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
                        errorMessage = "Error al conectar: {e.message}"
                    )
                }
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}