package com.tuhn.repository.RepositoryImpl

import com.tuhn.repository.ITodoRepository
import com.tuhn.repository.data.Todo
import com.tuhn.repository.data.source.ITodoDataSource

class TodoRepository(val localDataSource: ITodoDataSource) : ITodoRepository {
    override suspend fun insert(userId: Long, todo: Todo): Todo? {
        return localDataSource.insert(userId = userId,todo)
    }

    override suspend fun findByTitle(userId: Long, title: String): Todo? {
        return localDataSource.findByTitle(userId = userId,title)
    }

    override suspend fun find(userId: Long): List<Todo>? {
        return localDataSource.find(userId)
    }

    override suspend fun deleteByID(userId: Long, id: Long): Int {
        return localDataSource.deleteById(userId,id)
    }
}