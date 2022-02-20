package com.tuhn.plugins

import com.tuhn.auth.JwtService
import com.tuhn.auth.hash
import com.tuhn.repository.RepositoryImpl.TodoRepository
import com.tuhn.repository.RepositoryImpl.UserRepository
import com.tuhn.repository.data.source.local.TodoLocalDataSource
import com.tuhn.repository.data.source.local.UserLocalDataSource
import com.tuhn.routes.TodoRoute
import com.tuhn.routes.UserRoute
import io.ktor.routing.*
import io.ktor.locations.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {
    install(Locations) {
    }

    val hashFunction = { s: String -> hash(s) }
    routing {
        UserRoute( UserRepository(UserLocalDataSource()), JwtService(), hashFunction)
        TodoRoute(TodoRepository(TodoLocalDataSource()))

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
