package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCommand (
    val username: String,
    val email: String,
    val password: String
)