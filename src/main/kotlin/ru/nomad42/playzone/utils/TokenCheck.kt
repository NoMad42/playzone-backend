package ru.nomad42.playzone.utils

import ru.nomad42.playzone.database.tokens.Tokens

object TokenCheck {
    fun isValid(token: String): Boolean = Tokens.isExist(token)
    fun isAdmin(token: String): Boolean = token == System.getenv("ADMIN_TOKEN")
}
