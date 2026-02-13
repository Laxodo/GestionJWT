package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar

import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserCommand(
    val password: String
)