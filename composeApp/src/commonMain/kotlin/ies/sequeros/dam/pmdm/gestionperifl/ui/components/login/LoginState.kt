package ies.sequeros.dam.pmdm.gestionperifl.ui.components.login

data class LoginState(
    // Campos del formulario
    val email: String = "pedro@pedro.es",
    val password: String = "1234567Pp@",

    // UI States
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val isValid:Boolean = false,
    // Errores específicos de campo (validación local)
    val emailError: String? = null,
    val passwordError: String? = null,

    // Error global (ej: "Credenciales incorrectas" o "No hay internet")
    val errorMessage: String? = null
)