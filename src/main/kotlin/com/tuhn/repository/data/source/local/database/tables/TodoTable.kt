package com.tuhn.repository.data.source.local.database.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object TodoTable: Table() {
    val id : Column<Long> = long("id").autoIncrement().primaryKey()
    val user_id : Column<Long> = long("user_id").references(UserTable.id)
    val title = varchar("title", 512)
    val content = varchar("content", 512)
    val done = bool("done")
}