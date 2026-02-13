package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña

import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordCommand(
    val oldPassword: String,
    val newPassword: String
)