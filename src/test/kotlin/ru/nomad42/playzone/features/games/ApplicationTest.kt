package ru.nomad42.playzone.features.games

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import ru.nomad42.playzone.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application { configureRouting() }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello World!", response.bodyAsText())
    }
}
