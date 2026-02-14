package ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña

import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository

class ChangePasswordUseCase(private val repository: IUserRepository) {
    suspend operator fun invoke(changePasswordCommand: ChangePasswordCommand): Result<Boolean> {
        return repository.changePassword(changePasswordCommand)
    }
}