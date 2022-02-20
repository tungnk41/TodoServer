package com.tuhn.repository.data.source.local

import com.tuhn.repository.data.Todo
import com.tuhn.repository.data.User
import com.tuhn.repository.data.source.ITodoDataSource
import com.tuhn.repository.data.source.local.database.DatabaseFactory
import com.tuhn.repository.data.source.local.database.DatabaseFactory.query
import com.tuhn.repository.data.source.local.database.tables.TodoTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class TodoLocalDataSource : ITodoDataSource {

    override suspend fun insert(userId: Long,todo: Todo): Todo? {
        var statement: InsertStatement<Number>? = null
        query {
            statement = TodoTable.insert {
                it[user_id] = userId
                it[title] = todo.title
                it[content] = todo.content
                it[done] = todo.done
            }
        }
        return rowToTodo(statement?.resultedValues?.get(0))
    }

    override suspend fun update(userId: Long,todo: Todo): Int {
        val result = query {
            TodoTable.update(where = { TodoTable.user_id.eq(userId) and TodoTable.id.eq(todo.id ?: -1)}) {
                it[title] = todo.title
                it[content] = todo.content
                it[done] = todo.done
            }
        }
        return result
    }

    override suspend fun findById(userId: Long,id: Long): Todo? {
        val result = query {
            TodoTable
                .select { TodoTable.user_id.eq(userId) and TodoTable.id.eq(id) }
                .map { rowToTodo(it) }
                .singleOrNull()
        }

        return result
    }

    override suspend fun findByTitle(userId: Long,title: String): Todo? {
        val result = query {
            TodoTable
                .select { TodoTable.user_id.eq(userId) and TodoTable.title.eq(title) }
                .map { rowToTodo(it) }
                .singleOrNull()
        }
        return result
    }

    override suspend fun find(userId: Long): List<Todo>? {
        val result = query {
            TodoTable.select(where = {TodoTable.user_id.eq(userId)}).mapNotNull { rowToTodo(it) }
        }
        return result
    }

    override suspend fun deleteById(userId: Long, id: Long): Int {
        val result = query {
            TodoTable.deleteWhere {
                TodoTable.user_id.eq(userId) and TodoTable.id.eq(id)
            }
        }
        return result
    }

    private fun rowToTodo(row: ResultRow?): Todo? {
        if (row == null) {
            return null
        }
        return Todo(
            id = row[TodoTable.id],
            title = row[TodoTable.title],
            content = row[TodoTable.content],
            done = row[TodoTable.done]
        )
    }
}

