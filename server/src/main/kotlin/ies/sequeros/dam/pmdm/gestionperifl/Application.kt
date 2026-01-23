package ies.sequeros.dam.pmdm.gestionperifl

import ies.sequeros.dam.pmdm.gestionperifl.ktor_config.configureKoin
import ies.sequeros.dam.pmdm.gestionperifl.ktor_config.configureRouting
import ies.sequeros.dam.pmdm.gestionperifl.ktor_config.configureSecurity
import ies.sequeros.dam.pmdm.gestionperifl.ktor_config.configureSerialization
import ies.sequeros.dam.pmdm.gestionperifl.ktor_config.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import java.util.UUID


fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module() {
    //el orden importa
    //control de excepciones y http code status
    configureStatusPages()
    //Koin
    configureKoin()
    //configuración de json
    configureSerialization()

    //JWT
    configureSecurity()
    //routing
    configureRouting()

}
/*
fun ApplicationCall.requireUUID(iden: String = "id"): String {
    //se valida el uuid
    val id = parameters[iden] ?: throw MissingParameterException("El parámetro id es obligatorio")
    if (!id.isValidUUID()) {
        throw InvalidFormatParameterException("Formato de UUID inválido"," $id")
    }
    return try {
        UUID.fromString(id)
        id
    } catch (e: IllegalArgumentException) {
        throw InvalidFormatParameterException("Formato de UUID inválido"," $id")
    }

}*/