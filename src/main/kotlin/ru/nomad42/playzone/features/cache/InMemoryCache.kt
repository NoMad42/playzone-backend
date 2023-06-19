package ru.nomad42.playzone.features.cache

import ru.nomad42.playzone.features.register.RegisterRemoteRequest

object InMemoryCache {
    val userList: MutableList<RegisterRemoteRequest> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}
