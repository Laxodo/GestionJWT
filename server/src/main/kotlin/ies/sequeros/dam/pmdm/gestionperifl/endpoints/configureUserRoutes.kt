package ies.sequeros.dam.pmdm.gestionperifl.endpoints

import ies.sequeros.dam.pmdm.gestionperifl.endpoints.publico.loginEndPoint
import ies.sequeros.dam.pmdm.gestionperifl.endpoints.publico.refreshEndPoint
import ies.sequeros.dam.pmdm.gestionperifl.endpoints.publico.registerEndPoint
import ies.sequeros.dam.pmdm.gestionperifl.endpoints.user.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named
import org.koin.ktor.ext.inject

fun Route.configureUserRoutes() {

    route("api") {
        route("public") {
            //mejora el rendimiento solo se carga una vez el caso de uso
            //si se pone dentro se instancia cada vez


            registerEndPoint()
            loginEndPoint()
            refreshEndPoint()


        }
        authenticate("auth-user") {

            val baseUrl by inject<String>(named("baseUrl"))
            route("users") {
                route("me") {
                    changePasswordEndPoint()
                    deleteUserEndPoint()
                    profileEndPoint()
                    updateUserEndPoint()
                    updateUserImageEndPoint()


                }
            }
        }
    }

}
