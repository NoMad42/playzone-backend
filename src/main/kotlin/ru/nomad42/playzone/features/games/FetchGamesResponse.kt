package ru.nomad42.playzone.features.games

import kotlinx.serialization.Serializable

@Serializable
data class FetchGamesResponse(val games: List<GameResponse>)
