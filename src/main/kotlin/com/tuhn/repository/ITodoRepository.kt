package com.tuhn.repository

import com.tuhn.repository.data.Todo

interface ITodoRepository {
    suspend fun insert(userId: Long, todo: Todo): Todo?
    suspend fun findByTitle(userId: Long, title: String): Todo?
    suspend fun find(userId: Long): List<Todo>?
    suspend fun deleteByID(userId: Long, id: Long): Int
}