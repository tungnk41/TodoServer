package com.tuhn.repository.data.source.local

import com.tuhn.repository.data.User
import com.tuhn.repository.data.source.IUserDataSource
import com.tuhn.repository.data.source.local.database.DatabaseFactory
import com.tuhn.repository.data.source.local.database.DatabaseFactory.query
import com.tuhn.repository.data.source.local.database.tables.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserLocalDataSource : IUserDataSource {

    override suspend fun insert(user: User): User? {
        var statement: InsertStatement<Number>? = null
        query {
            statement = UserTable.insert {
                it[username] = user.username
                it[password] = user.password
            }
        }

        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun update(user: User): Int {
        val result = query {
            UserTable.update(where = { UserTable.id.eq(user.id ?: -1) } ) {
                it[username] = user.username
                it[password] = user.password
            }
        }

        return result
    }

    override suspend fun findById(id: Long): User? {
        val result = query {
            UserTable
                .select { UserTable.id.eq(id) }
                .map { rowToUser(it) }
                .singleOrNull()
        }

        return result
    }

    override suspend fun findByName(username: String): User? {
        val result = query {
            UserTable
                .select { UserTable.username.eq(username) }
                .map { rowToUser(it) }.
                singleOrNull()
        }
        return result
    }

    override suspend fun find(): List<User>? {
        val result = query {
            UserTable.selectAll().mapNotNull { rowToUser(it) }
        }
        return result
    }

    override suspend fun deleteById(id: Long): Int {
        val result = query {
            UserTable.deleteWhere {
                UserTable.id.eq(id)
            }
        }
        return result
    }

    private fun rowToUser(row: ResultRow?): User? {
        if (row == null) {
            return null
        }
        return User(
            id = row[UserTable.id],
            username = row[UserTable.username],
            password = row[UserTable.password]
        )
    }
}