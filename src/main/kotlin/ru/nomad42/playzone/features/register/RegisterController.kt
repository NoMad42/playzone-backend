package ru.nomad42.playzone.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.nomad42.playzone.database.tokens.TokenDTO
import ru.nomad42.playzone.database.tokens.Tokens
import ru.nomad42.playzone.database.users.UserDTO
import ru.nomad42.playzone.database.users.Users
import ru.nomad42.playzone.utils.isValidEmail
import java.util.*

class RegisterController(private val call: ApplicationCall) {
    suspend fun registerNewUser() {
        val registerRemoteRequest = call.receive<RegisterRemoteRequest>()
        val userDTO = Users.fetchUser(registerRemoteRequest.login)

        if (!registerRemoteRequest.email.isValidEmail()) {
            call.respond(HttpStatusCode.Conflict, "Email is not valid")

            return
        }

        if (userDTO != null) {
            call.respond(HttpStatusCode.BadRequest, "User already existed")

            return
        }

        val token = UUID.randomUUID()

        try {
            Users.insert(
                UserDTO(
                    login = registerRemoteRequest.login,
                    password = registerRemoteRequest.password,
                    username = registerRemoteRequest.username,
                    email = registerRemoteRequest.email,
                )
            )
        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.BadRequest, "User already existed")

            return
        }

        Tokens.insert(
            TokenDTO(
                id = UUID.randomUUID(),
                login = registerRemoteRequest.login,
                token = token,
            )
        )

        call.respond(RegisterRemoteResponse(token = token.toString()))
    }
}
