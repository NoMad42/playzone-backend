package ru.nomad42.playzone.features.games

import kotlinx.serialization.Serializable
import ru.nomad42.playzone.database.games.GameDTO
import java.util.*

@Serializable
data class CreateGameRequest(
    val title: String,
    val description: String,
    val version: String,
    val size: String
) {
    fun mapToGameDTO(): GameDTO = GameDTO(
        id = UUID.randomUUID(),
        name = title,
        description = description,
        version = version,
        weight = size,
        downloadCount = 0,
        logo = "",
        backdrop = null
    )
}
