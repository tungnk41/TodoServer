package com.tuhn.repository.data.source

import com.tuhn.repository.data.Todo

interface ITodoDataSource {
    suspend fun insert(userId: Long,todo: Todo): Todo?
    suspend fun update(userId: Long,todo: Todo): Int
    suspend fun findById(userId: Long,id: Long): Todo?
    suspend fun findByTitle(userId: Long,title: String): Todo?
    suspend fun find(userId: Long) : List<Todo>?
    suspend fun deleteById(userId: Long,id: Long): Int
}