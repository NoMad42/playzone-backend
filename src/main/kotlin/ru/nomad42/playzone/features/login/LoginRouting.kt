package ru.nomad42.playzone.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.nomad42.playzone.features.cache.InMemoryCache
import ru.nomad42.playzone.features.cache.TokenCache
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val request = call.receive(LoginRemoteRequest::class)

            val first = InMemoryCache.userList.firstOrNull { it.login == request.login }

            if (first == null || first.password != request.password) {
                call.respond(HttpStatusCode.BadRequest, "Invalid credentials")
            }

            val token = UUID.randomUUID().toString()
            InMemoryCache.token.add((TokenCache(login = request.login, token = token)))
            call.respond(LoginRemoteResponse(token = token))

            return@post
        }
    }
}
