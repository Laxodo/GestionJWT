package ies.sequeros.dam.pmdm.gestionperifl.endpoints.publico

import ies.sequeros.dam.pmdm.gestionperifl.application.user.refresh.RefreshTokenUseCase
import ies.sequeros.dam.pmdm.gestionperifl.application.user.refresh.RefreshTokenUserCommand
import ies.sequeros.dam.pmdm.gestionperifl.ktor_config.plugins.ValidateSchema
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.refreshEndPoint() {
    val refreshUserUseCase by inject<RefreshTokenUseCase>()
    route("refresh") {
        install(ValidateSchema) {
            schemaPath = "json_schemas/refresh-token-command.schema.json"
        }

        post() {

            call.receive<String>()
            val command = call.receive<RefreshTokenUserCommand>()
            var item = refreshUserUseCase(command)
            call.respond(HttpStatusCode.OK, item)

        }
    }
}