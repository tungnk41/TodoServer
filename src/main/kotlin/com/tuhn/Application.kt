package com.tuhn

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.tuhn.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureSecurity()
        configureMonitoring()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
