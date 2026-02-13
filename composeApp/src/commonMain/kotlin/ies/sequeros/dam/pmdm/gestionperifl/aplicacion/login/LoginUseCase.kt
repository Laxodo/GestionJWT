package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.UserSessionManager
import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.LoginEntity


class LoginUseCase(private val repository: IUserRepository, private val userSessionManager: UserSessionManager) {
    suspend fun invoke(loginCommand: LoginCommand): Result<LoginEntity> {
        val result = repository.login(loginCommand)
        result.onSuccess {
            item -> userSessionManager.saveSession(item.access_token, item.refresh_token, item.id_token)
        }
        return result
    }
}