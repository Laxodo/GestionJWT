package ies.sequeros.dam.pmdm.gestionperifl.application.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCommand (
    val username: String,
    val email: String,
    val password: String
)