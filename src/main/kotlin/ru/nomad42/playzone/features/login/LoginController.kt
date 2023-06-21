package ru.nomad42.playzone.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.nomad42.playzone.database.tokens.TokenDTO
import ru.nomad42.playzone.database.tokens.Tokens
import ru.nomad42.playzone.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {
    suspend fun performLogin() {
        val request = call.receive<LoginRemoteRequest>()

        val first = Users.fetchUser(request.login)

        if (first == null || first.password != request.password) {
            call.respond(HttpStatusCode.BadRequest, "Invalid credentials")
        }

        val token = UUID.randomUUID()

        Tokens.insert(
            TokenDTO(
                id = UUID.randomUUID(),
                login = request.login,
                token = token,
            )
        )

        call.respond(LoginRemoteResponse(token = token.toString()))
    }
}
