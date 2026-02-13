package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginCommand(
    val email: String,
    val password: String
)