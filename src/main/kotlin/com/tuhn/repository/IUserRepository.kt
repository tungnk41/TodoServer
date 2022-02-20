package com.tuhn.repository

import com.tuhn.repository.data.User

interface IUserRepository {
    suspend fun insert(user: User): User?
    suspend fun findByName(userName: String): User?
    suspend fun findById(id: Long): User?
}