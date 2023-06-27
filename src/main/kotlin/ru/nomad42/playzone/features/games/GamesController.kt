package ru.nomad42.playzone.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.nomad42.playzone.database.games.Games
import ru.nomad42.playzone.utils.TokenCheck

class GamesController(private val call: ApplicationCall) {
    suspend fun fetchGames() {
        val token = getToken(call)

        if (!(TokenCheck.isValid(token) || TokenCheck.isAdmin(token))) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")

            return
        }

        call.respond(FetchGamesResponse(Games.fetchGames().map { it.mapToGameResponse() }))
    }

    suspend fun addGame() {
        val token = getToken(call)

        if (!(TokenCheck.isValid(token) || TokenCheck.isAdmin(token))) {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")

            return
        }

        val game = call.receive<CreateGameRequest>().mapToGameDTO()
        Games.insert(game)
        call.respond(game.mapToGameResponse())
    }

    private fun getToken(call: ApplicationCall): String = call.request
        .headers["Authorization"]
        .orEmpty()
        .removePrefix("Bearer ")
}
