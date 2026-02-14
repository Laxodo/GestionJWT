package ies.sequeros.dam.pmdm.gestionperifl.dominio

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val image: String?,
    val status: String
)