package ies.sequeros.dam.pmdm.gestionperifl.ui.delete

import kotlinx.serialization.Serializable

@Serializable
data class DeleteState(
    val password: String = "",

    val passwordErr: String? = null,

    val isValid: Boolean = false,
    val isLoading: Boolean = false,
    val messageError: String? = null,
    val isDeleteSuccess: Boolean = false
)