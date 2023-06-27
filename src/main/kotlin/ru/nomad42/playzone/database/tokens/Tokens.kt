package ru.nomad42.playzone.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object Tokens : Table("tokens") {
    private val id = Tokens.uuid("id")
    private val login = Tokens.varchar("login", 255)
    private val token = Tokens.uuid("token")

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[id] = tokenDTO.id
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }

    fun isExist(token: String): Boolean = transaction {
        Tokens.select {
            Tokens.token eq UUID.fromString(token)
        }.firstOrNull() != null
    }
}
