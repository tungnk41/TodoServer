package com.tuhn.repository.RepositoryImpl

import com.tuhn.repository.IUserRepository
import com.tuhn.repository.data.User
import com.tuhn.repository.data.source.IUserDataSource

class UserRepository(val localDataSource: IUserDataSource) : IUserRepository {
    override suspend fun insert(user: User): User? {
        return localDataSource.insert(user)
    }

    override suspend fun findByName(userName: String): User? {
        return localDataSource.findByName(userName)
    }

    override suspend fun findById(id: Long): User? {
        return localDataSource.findById(id)
    }
}