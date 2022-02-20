package com.tuhn.plugins

import com.tuhn.auth.JwtService
import com.tuhn.auth.hash
import com.tuhn.repository.DatabaseFactory
import com.tuhn.repository.RepositoryTodo
import com.tuhn.routes.todos
import com.tuhn.routes.users
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
    install(Locations) {
    }

    DatabaseFactory.init()
    val db = RepositoryTodo()
    val jwtService = JwtService()
    val hashFunction = { s: String -> hash(s) }
    routing {
        users(db, jwtService, hashFunction)
        todos(db)

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
