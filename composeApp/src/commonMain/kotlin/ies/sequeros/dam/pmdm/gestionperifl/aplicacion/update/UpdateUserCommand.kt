package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.update

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserCommand(
    val name: String,
    val estado: String
)