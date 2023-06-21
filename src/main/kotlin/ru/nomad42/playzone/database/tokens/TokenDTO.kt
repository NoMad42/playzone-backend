package ru.nomad42.playzone.database.tokens

import java.util.UUID

class TokenDTO(
    val id: UUID,
    val login: String,
    val token: UUID,
)
