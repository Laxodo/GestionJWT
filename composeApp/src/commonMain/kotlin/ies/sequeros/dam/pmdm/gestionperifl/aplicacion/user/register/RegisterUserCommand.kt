package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.user.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserCommand(
    val username: String,
    val password: String,
    val email: String
)