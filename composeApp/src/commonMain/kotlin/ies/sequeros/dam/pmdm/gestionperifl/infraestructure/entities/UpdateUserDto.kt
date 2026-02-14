package ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities

data class UpdateUserDto(
    val id: String,
    val username: String,
    val email: String,
    val image: String? = null
)