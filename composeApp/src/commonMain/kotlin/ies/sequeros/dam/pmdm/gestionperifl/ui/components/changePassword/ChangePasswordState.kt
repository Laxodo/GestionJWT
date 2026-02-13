package ies.sequeros.dam.pmdm.gestionperifl.ui.components.changePassword

data class ChangePasswordState (
    val password: String = "",
    val newPassword: String = "",

    val isLoading: Boolean = false,
    val isChangePasswordSuccess: Boolean = false,
    val isValid: Boolean = false,

    val passwordError: String? = null,
    val newPasswordError: String? = null,

    val errorMessage: String? = null
)