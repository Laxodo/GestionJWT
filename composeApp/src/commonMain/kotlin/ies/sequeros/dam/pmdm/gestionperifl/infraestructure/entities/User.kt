package ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities

data class User(
    val access_token: String,
    val id_token: String,
    val expires_in: Long,
    val token_type: String = "Bearer",
    val refresh_token: String? = null
)
