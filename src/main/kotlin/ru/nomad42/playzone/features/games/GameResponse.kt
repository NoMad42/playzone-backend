package ru.nomad42.playzone.features.games

import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val id: String,
    val title: String,
    val description: String,
    val version: String,
    val weight: String,
)
