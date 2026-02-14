package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.update

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.UpdateUserDto

class UpdateUserUseCase(private val repository: IUserRepository) {
    suspend operator fun invoke(updateUserCommand: UpdateUserCommand): Result<UpdateUserDto> {
        return repository.updateUser(updateUserCommand)
    }
}