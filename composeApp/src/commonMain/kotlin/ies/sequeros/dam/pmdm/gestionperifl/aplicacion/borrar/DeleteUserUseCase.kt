package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository

class DeleteUserUseCase(private val repository: IUserRepository) {
    suspend fun invoke(deleteUserCommand: DeleteUserCommand): Result<Boolean>{
        return repository.delete(deleteUserCommand)
    }
}