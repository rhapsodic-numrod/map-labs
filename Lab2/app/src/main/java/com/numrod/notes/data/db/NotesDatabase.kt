package com.numrod.notes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.numrod.notes.data.dao.NoteDao
import com.numrod.notes.data.dao.UserDao
import com.numrod.notes.data.models.Note
import com.numrod.notes.data.models.User
import com.numrod.notes.util.DateConverter
import com.numrod.notes.util.UUIDConverter

@Database(entities = [Note::class, User::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
public abstract class NotesDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

}