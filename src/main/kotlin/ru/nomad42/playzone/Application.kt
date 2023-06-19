package ru.nomad42.playzone

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.nomad42.playzone.features.login.configureLoginRouting
import ru.nomad42.playzone.features.register.configureRegisterRouting
import ru.nomad42.playzone.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
    configureRouting()
}
