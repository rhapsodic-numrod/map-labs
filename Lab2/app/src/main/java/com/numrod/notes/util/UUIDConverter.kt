package com.numrod.notes.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUID(uuid: UUID): String{
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String): UUID{
        return UUID.fromString(string)
    }
}