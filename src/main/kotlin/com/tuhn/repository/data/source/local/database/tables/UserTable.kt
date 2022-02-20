package com.tuhn.repository.data.source.local.database.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val id : Column<Long> = long("id").autoIncrement().primaryKey()
    val username = varchar("username", 128).uniqueIndex()
    val password = varchar("password", 64)
}