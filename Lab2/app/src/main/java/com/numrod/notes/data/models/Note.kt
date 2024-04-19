package com.numrod.notes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.numrod.notes.util.DateConverter
import com.numrod.notes.util.UUIDConverter
import java.util.Date
import java.time.Instant
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date_created")
    val dateCreated: Date = Date.from(Instant.now()),
)
