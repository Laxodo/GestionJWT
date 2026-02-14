package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.RegisterDto


class RegisterUseCase(private val repository: IUserRepository) {
    suspend operator fun invoke(registerCommand: RegisterCommand): Result<RegisterDto> {
        return repository.register(registerCommand)
    }
}