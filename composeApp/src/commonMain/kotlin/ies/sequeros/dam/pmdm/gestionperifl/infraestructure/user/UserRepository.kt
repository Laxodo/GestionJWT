package ies.sequeros.dam.pmdm.gestionperifl.infraestructure.user

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.user.register.RegisterUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import io.ktor.client.HttpClient

class UserRepository(private val url: String, private val _client: HttpClient) : IUserRepository {
    override suspend fun register(registerUserCommand: RegisterUserCommand) {
        TODO("Not yet implemented")
    }

}