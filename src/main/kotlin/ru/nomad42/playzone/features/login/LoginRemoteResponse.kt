package ru.nomad42.playzone.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRemoteResponse(
    val token: String
)
