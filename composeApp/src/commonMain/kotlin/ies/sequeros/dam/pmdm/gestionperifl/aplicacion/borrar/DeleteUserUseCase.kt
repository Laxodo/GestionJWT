package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository

class DeleteUserUseCase(private val repository: IUserRepository) {
    suspend fun invoke(){
        TODO("Implementar el repositorio para borrar un usuario")
    }
}