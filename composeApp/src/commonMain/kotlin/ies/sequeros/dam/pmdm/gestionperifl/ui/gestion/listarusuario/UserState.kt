package ies.sequeros.dam.pmdm.gestionperifl.ui.gestion.listarusuario

import ies.sequeros.dam.pmdm.gestionperifl.dominio.User

data class UserState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)