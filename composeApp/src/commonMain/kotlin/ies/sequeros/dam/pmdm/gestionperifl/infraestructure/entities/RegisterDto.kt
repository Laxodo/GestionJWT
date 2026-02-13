package ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
    val id: String,
    val username: String,
    val password: String
)