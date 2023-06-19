package ru.nomad42.playzone.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRemoteRequest(
    val login: String,
    val password: String
)
