package ru.nomad42.playzone

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.nomad42.playzone.features.games.configureGamesRouting
import ru.nomad42.playzone.features.login.configureLoginRouting
import ru.nomad42.playzone.features.register.configureRegisterRouting
import ru.nomad42.playzone.plugins.*

fun main() {
    Database.connect(
        System.getenv("JDBC_DATABASE_URL"),
        driver = "org.postgresql.Driver",
        System.getenv("JDBC_DATABASE_LOGIN"),
        System.getenv("JDBC_DATABASE_PASSWORD")
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
    configureGamesRouting()
    configureRouting()
}
