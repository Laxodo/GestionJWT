package ies.sequeros.dam.pmdm.gestionperifl.application.user.changepassword

import ies.sequeros.dam.pmdm.gestionperifl.application.exceptions.BusinessException
import ies.sequeros.dam.pmdm.gestionperifl.application.exceptions.InvalidCredentialsException
import ies.sequeros.dam.pmdm.gestionperifl.application.exceptions.NotFoundException
import ies.sequeros.dam.pmdm.gestionperifl.application.services.ITokenService
import ies.sequeros.dam.pmdm.gestionperifl.application.user.refresh.RefreshDto
import ies.sequeros.dam.pmdm.gestionperifl.application.user.refresh.RefreshTokenUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.domain.repositories.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.domain.services.IPasswordEncoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class ChangePasswordUseCase(
    val repository: IUserRepository,
    val passwordEnconder: IPasswordEncoder,

) {
    suspend operator fun invoke(id: UUID, command: ChangePasswordCommand): Unit =
        withContext(Dispatchers.IO) {
            //val user=repository.findById(id)?: throw  InvalidCredentialsException("User")
            val oldPassword=repository.getPasswordHash(id)?:throw InvalidCredentialsException("User")
            if(passwordEnconder.matches(command.newPassword,oldPassword)){
                  throw BusinessException("La nueva clave ha de ser diferente a la anterior")
            }
            val match=passwordEnconder.matches(command.oldPassword,oldPassword)

            if(!match){
                throw  InvalidCredentialsException("User")
            }
            val newPassword=passwordEnconder.encode(command.newPassword)
            repository.updatePassword(id,newPassword)
        }
}