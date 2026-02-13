package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository


class RegisterUseCase(private val repository: IUserRepository) {
    suspend operator fun invoke(registerCommand: RegisterCommand): Result<Boolean> {
        return repository.register(registerCommand)
    }
}