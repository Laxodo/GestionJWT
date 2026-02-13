package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.UserSessionManager
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login.LoginCommand
import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.LoginDto


class LoginUseCase(private val repository: IUserRepository, private val userSessionManager: UserSessionManager) {
    suspend fun invoke(loginCommand: LoginCommand): Result<LoginDto> {
        val result = repository.login(loginCommand)
        result.onSuccess {
            item -> userSessionManager.saveSession(item.access_token, item.refresh_token, item.id_token)
        }
        return result
    }
}