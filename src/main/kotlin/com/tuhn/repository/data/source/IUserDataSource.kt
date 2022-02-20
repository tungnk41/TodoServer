package com.tuhn.repository.data.source

import com.tuhn.repository.data.User

interface IUserDataSource {
    suspend fun insert(user: User): User?
    suspend fun update(user: User): Int
    suspend fun findById(id: Long): User?
    suspend fun findByName(username: String): User?
    suspend fun find() : List<User>?
    suspend fun deleteById(id: Long): Int
}