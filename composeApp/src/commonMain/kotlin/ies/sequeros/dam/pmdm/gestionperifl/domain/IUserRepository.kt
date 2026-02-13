package ies.sequeros.dam.pmdm.gestionperifl.domain

import ies.sequeros.dam.pmdm.gestionperifl.application.login.commands.LoginCommand
import ies.sequeros.dam.pmdm.gestionperifl.application.register.RegisterCommand

interface IUserRepository {
    /*suspend fun login(loginCommand: LoginCommand): Result<Boolean>
    suspend fun delete(deleteUserCommand: DeleteUSerCommand): Result<Boolean>
    suspend fun changePassword(changePasswordCommand: ChangePasswordCommand): Result<Boolean>*/
    suspend fun register(registerCommand: RegisterCommand): Result<Boolean>
}