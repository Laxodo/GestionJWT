package ies.sequeros.dam.pmdm.gestionperifl.dominio

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar.DeleteUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login.LoginCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterCommand
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.TokenJwt
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.LoginDto
import io.ktor.http.cio.Request

interface IUserRepository {
    suspend fun login(loginCommand: LoginCommand): Result<LoginDto>
    suspend fun register(registerCommand: RegisterCommand): Result<Boolean>
    suspend fun getUser(): Result<User>
    suspend fun delete(deleteUserCommand: DeleteUserCommand): Result<Boolean>
    suspend fun changePassword(changePasswordCommand: ChangePasswordCommand): Result<Boolean>
}