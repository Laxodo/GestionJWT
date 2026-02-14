package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.listarusuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListarUsuarioFormViewModel(
    getUserUseCase: GetUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state.asStateFlow()

    init{
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            getUserUseCase.invoke()
                .onSuccess { user ->
                    _state.update { it.copy(user = user, isLoading = false) }
                }
                .onFailure { error ->
                    _state.update { it.copy(errorMessage = error.message, isLoading = false) }
                }
        }
    }

}