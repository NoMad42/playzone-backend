package ru.nomad42.playzone.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.nomad42.playzone.features.cache.InMemoryCache
import ru.nomad42.playzone.features.cache.TokenCache
import ru.nomad42.playzone.utils.isValidEmail
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val request = call.receive(RegisterRemoteRequest::class)

            if (!request.email.isValidEmail()) {
                call.respond(HttpStatusCode.Conflict, "Email is not valid")

                return@post
            }

            if (InMemoryCache.userList.map { it.login }.contains(request.login)) {
                call.respond(HttpStatusCode.BadRequest, "User already existed")

                return@post
            }

            val token = UUID.randomUUID().toString()
            InMemoryCache.userList.add(request)
            InMemoryCache.token.add((TokenCache(login = request.login, token = token)))

            call.respond(RegisterRemoteResponse(token = token))
        }
    }
}
