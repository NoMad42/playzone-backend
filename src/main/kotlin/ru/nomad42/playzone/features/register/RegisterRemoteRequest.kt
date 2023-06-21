package ru.nomad42.playzone.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRemoteRequest(
    val login: String,
    val password: String,
    val username: String,
    val email: String,
)
