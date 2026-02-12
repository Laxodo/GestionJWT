package ies.sequeros.dam.pmdm.gestionperifl.domain

data class User (
    val id: String,
    val name: String,
    val email: String,
    val image: String?,
    val status: String
)