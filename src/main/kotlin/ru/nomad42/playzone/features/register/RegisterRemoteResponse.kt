package ru.nomad42.playzone.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRemoteResponse(
    val token: String
)
