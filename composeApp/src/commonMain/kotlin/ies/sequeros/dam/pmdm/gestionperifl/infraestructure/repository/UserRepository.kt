package ies.sequeros.dam.pmdm.gestionperifl.infraestructure.repository

import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.borrar.DeleteUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.cambiarcontraseña.ChangePasswordCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.login.LoginCommand
import ies.sequeros.dam.pmdm.gestionperifl.aplicacion.register.RegisterCommand
import ies.sequeros.dam.pmdm.gestionperifl.dominio.IUserRepository
import ies.sequeros.dam.pmdm.gestionperifl.dominio.User
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.LoginEntity
import ies.sequeros.dam.pmdm.gestionperifl.infraestructure.entities.RegisterDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class UserRepository(private val url:String,private val _client: HttpClient): IUserRepository {
    override suspend fun login(loginCommand: LoginCommand): Result<LoginEntity> {
        return runCatching {
            val request = this._client.post("$url/api/public/login") {
                contentType(ContentType.Application.Json)
                setBody(loginCommand)
            }

            when(request.status){
                HttpStatusCode.OK, HttpStatusCode.Created -> ""
                HttpStatusCode.BadRequest -> throw Exception("Usuario o contraseña no validos")
                HttpStatusCode.Conflict -> throw Exception("Usuario o contraseña incorrecto")
                else -> throw Exception(request.status.description)
            }

            val item = request.body<LoginEntity>()
            item
        }
    }

    override suspend fun register(registerCommand: RegisterCommand): Result<RegisterDto> {
        return runCatching {
            val request = this._client.post("$url/api/public/register") {
                contentType(ContentType.Application.Json)
                setBody(registerCommand)
            }

            when(request.status){
                HttpStatusCode.Created -> ""
                HttpStatusCode.BadRequest -> throw Exception("Usuario o contraseña no validos")
                HttpStatusCode.Conflict -> throw Exception("Usuario o contraseña incorrecto")
                else -> throw Exception(request.status.description)
            }

            val item = request.body<RegisterDto>()
            item
        }
    }

    override suspend fun getUser(): Result<User> {
        return runCatching {
            val request = this._client.get("$url/api/users/me")
            request.body<User>()
        }
    }

    override suspend fun delete(deleteUserCommand: DeleteUserCommand): Result<Boolean> {
        // TODO: Comporbar funcionamiento
        return runCatching {
            val request = this._client.delete("$url/api/users/me"){
                contentType(ContentType.Application.Json)
                setBody(deleteUserCommand)
            }

            if(request.status.value !in 200..<300)
                throw Exception("${request.status.value}-${request.status.description}")

            true
        }
    }

    override suspend fun changePassword(changePasswordCommand: ChangePasswordCommand): Result<Boolean> {
        TODO("Not yet implemented")
    }
}