package ru.nomad42.playzone.database.games

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Games : Table("games") {
    private val id = Games.uuid("id")
    private val name = Games.varchar("name", 255)
    private val backdrop = Games.varchar("backdrop", 255).nullable()
    private val logo = Games.varchar("logo", 255)
    private val description = Games.text("description")
    private val downloadCount = Games.integer("download_count")
    private val version = Games.varchar("version", 255)
    private val weight = Games.varchar("weight", 255)

    fun insert(gameDTO: GameDTO) {
        transaction {
            Games.insert {
                it[id] = gameDTO.id
                it[name] = gameDTO.name
                it[backdrop] = gameDTO.backdrop
                it[logo] = gameDTO.logo
                it[description] = gameDTO.description
                it[downloadCount] = gameDTO.downloadCount
                it[version] = gameDTO.version
                it[weight] = gameDTO.weight
            }
        }
    }

    fun fetchGames(): List<GameDTO> {
        return try {
            transaction {
                Games.selectAll().toList().map {
                    GameDTO(
                        id = it[Games.id],
                        name = it[name],
                        backdrop = it[backdrop],
                        logo = it[logo],
                        description = it[description],
                        downloadCount = it[downloadCount],
                        version = it[version],
                        weight = it[weight],
                    )
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
