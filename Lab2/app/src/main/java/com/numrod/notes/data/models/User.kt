package com.numrod.notes.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index("username")])
data class User(
    @PrimaryKey
    val username: String,
    val password: String,
)
