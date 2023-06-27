package ru.nomad42.playzone.database.games

import ru.nomad42.playzone.features.games.GameResponse
import java.util.UUID

data class GameDTO(
    val id: UUID,
    val name: String,
    val backdrop: String?,
    val logo: String,
    val description: String,
    val downloadCount: Int,
    val version: String,
    val weight: String,
) {
    fun mapToGameResponse(): GameResponse = GameResponse(
        id = id.toString(),
        title = name,
        description = description,
        version = version,
        weight = weight
    )
}
