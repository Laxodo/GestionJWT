package ies.sequeros.dam.pmdm.gestionperifl.aplicacion

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.dominio.User

class GetUserUseCase(private val repository: IUserRepository) {
    suspend fun invoke(): Result<User>{
        return repository.getUser()
    }
}