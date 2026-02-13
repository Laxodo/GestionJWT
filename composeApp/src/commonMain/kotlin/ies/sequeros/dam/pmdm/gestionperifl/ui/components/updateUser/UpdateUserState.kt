package ies.sequeros.dam.pmdm.gestionperifl.ui.components.updateUser

data class UpdateUserState(
    val newUsername: String = "",
    val newStatus: String = "active",

    val isLoading: Boolean = false,
    val isUpdateUserSuccess: Boolean = false,
    val isValid: Boolean = false,

    val newUsernameError: String? = null,

    val errorMessage: String? = null
)