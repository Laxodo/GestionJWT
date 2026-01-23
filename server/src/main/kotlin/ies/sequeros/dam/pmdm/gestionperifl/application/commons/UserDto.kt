package ies.sequeros.dam.pmdm.gestionperifl.application.commons

import ies.sequeros.dam.pmdm.gestionperifl.application.serializers.UUIDSerializer
import ies.sequeros.dam.pmdm.gestionperifl.domain.entities.User
import ies.sequeros.dam.pmdm.gestionperifl.domain.entities.UserStatus
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.UserJPA
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDto (
    @Serializable(with = UUIDSerializer::class)
    val id : UUID,
    val username: String, val email: String, val image: String?=null ) {
 companion object {
     fun fromDomain(user: User) : UserDto {
         return  UserDto(user.id, username = user.name, email = user.email, user.image?:null)

     }
 }
}
