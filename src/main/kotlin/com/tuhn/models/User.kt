package com.tuhn.models

import io.ktor.auth.Principal
import java.io.Serializable

data class User(val userId : Int, val email: String, val displayName: String, val password: String) : Serializable, Principal