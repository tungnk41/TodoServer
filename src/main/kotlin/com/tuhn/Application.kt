package com.tuhn

import com.tuhn.repository.data.source.local.database.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.tuhn.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {
        DatabaseFactory.init()
        configureSecurity()
        configureMonitoring()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
