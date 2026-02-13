package ies.sequeros.dam.pmdm.gestionperifl.dominio

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar.DeleteUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login.LoginCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterCommand
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.LoginEntity
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.update.UpdateUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.TokenJwt
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.LoginDto
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.RegisterDto
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.UpdateUserDto
import io.ktor.http.cio.Request

interface IUserRepository {
    suspend fun login(loginCommand: LoginCommand): Result<LoginEntity>
    suspend fun register(registerCommand: RegisterCommand): Result<RegisterDto>
    suspend fun getUser(): Result<User>
    suspend fun delete(deleteUserCommand: DeleteUserCommand): Result<Boolean>
    suspend fun changePassword(changePasswordCommand: ChangePasswordCommand): Result<Boolean>
    suspend fun updateUser(updateUserCommand: UpdateUserCommand): Result<UpdateUserDto>
}