package ies.sequeros.dam.pmdm.gestionperifl.ui.register

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val verifyPassword: String = "",
    val email: String = "",

    val usernameError: String? = null,
    val passwordError: String? = null,
    val verifyPasswordError: String? = null,
    val emailError: String? = null,

    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val isValid:Boolean = false,

    val errorMessage: String? = null,
)