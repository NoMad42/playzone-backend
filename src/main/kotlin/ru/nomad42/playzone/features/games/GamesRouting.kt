package ru.nomad42.playzone.features.games

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGamesRouting() {
    routing {
        get("/games/") {
            GamesController(call).fetchGames()
        }

        post("/games/add") {
            GamesController(call).addGame()
        }
    }
}
