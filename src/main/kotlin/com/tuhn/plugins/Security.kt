package com.tuhn.plugins

import io.ktor.sessions.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.tuhn.auth.JwtService
import com.tuhn.auth.Session
import com.tuhn.repository.RepositoryImpl.UserRepository
import com.tuhn.repository.data.source.local.UserLocalDataSource
import io.ktor.application.*

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<Session>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    authentication {
//        jwt {
//            val jwtAudience = environment.config.property("jwt.audience").getString()
//            realm = environment.config.property("jwt.realm").getString()
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256("secret"))
//                    .withAudience(jwtAudience)
//                    .withIssuer(environment.config.property("jwt.domain").getString())
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
//            }
//        }

        val repository = UserRepository(UserLocalDataSource())
        val jwtService = JwtService()
        jwt("jwt") {
            verifier(jwtService.verifier)
            realm = "Todo Server"
            validate {
                val payload = it.payload
                val claim = payload.getClaim("id")
                val claimString = claim.asLong()
                val user = repository.findById(claimString)
                user
            }
        }
    }
}
