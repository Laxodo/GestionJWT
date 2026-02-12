package ies.sequeros.dam.pmdm.gestionperifl.ui.components.register

import androidx.compose.ui.input.pointer.PointerEventPass

data class RegisterState(
    // Campos Formulario
    val email: String,
    val username: String,
    val password: String,

    // UI States
    val isLoading: Boolean = false,
    val isRegisterSuccess: Boolean = false,
    val isValid: Boolean = false,

    //Errores específicos de campo
    val emailError: String? = null,
    val usernameError: String? = null,
    val passwordError: String? = null,

    //Error global (No internet)
    val errorMessage: String? = null
)