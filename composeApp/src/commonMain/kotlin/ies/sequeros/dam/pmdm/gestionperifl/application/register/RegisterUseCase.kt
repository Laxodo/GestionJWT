package ies.sequeros.dam.pmdm.gestionperifl.application.register

import ies.sequeros.dam.pmdm.gestionperifl.domain.IUserRepository


class RegisterUseCase(private val repository: IUserRepository) {
    suspend fun invoke(registerCommand: RegisterCommand): Result<Boolean> {
        return repository.register(registerCommand)
    }
}