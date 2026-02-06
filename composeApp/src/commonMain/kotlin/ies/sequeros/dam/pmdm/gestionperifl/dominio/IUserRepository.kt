package ies.sequeros.dam.pmdm.gestionperifl.dominio

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.user.register.RegisterUserCommand

interface IUserRepository {
    suspend fun register(registerUserCommand: RegisterUserCommand)
}