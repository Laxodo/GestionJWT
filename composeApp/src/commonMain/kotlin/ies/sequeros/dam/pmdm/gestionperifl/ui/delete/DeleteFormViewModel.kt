package ies.sequeros.dam.pmdm.gestionperifl.ui.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar.DeleteUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar.DeleteUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeleteFormViewModel(
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(DeleteState())
    val uiState = _uiState.asStateFlow()
    val isFormValid = MutableStateFlow(false)

    fun onChangePassword(passwd: String){
        _uiState.update {
            it.copy(
                password = passwd,
                passwordErr = if (passwd.length >= 6) null else "Mínimo 6 caracteres"
            )
        }
        validateForm()
    }

    private fun validateForm() {
        val s = _uiState.value
        isFormValid.value = s.password.isNotBlank() &&
                s.passwordErr == null
        _uiState.value=uiState.value.copy( isValid = isFormValid.value)
    }

    fun delete(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, messageError = null) }
            val request = DeleteUserCommand(
                password = _uiState.value.password
            )

            val result = deleteUserUseCase.invoke(request).onSuccess {
                _uiState.update { it.copy(isLoading = false, isDeleteSuccess = true) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false, isDeleteSuccess = false) }
                println("Error aqui: ${it.message}")
                val error = it.message
                _uiState.update { it.copy(messageError = error!!) }
            }
        }
    }

}