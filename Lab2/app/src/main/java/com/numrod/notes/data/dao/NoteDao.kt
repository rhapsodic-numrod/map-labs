package com.numrod.notes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.numrod.notes.data.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //    Create and Update
    @Insert
    suspend fun insert(note: Note)

    //    Read
    @Query("SELECT * FROM notes ORDER BY date_created ASC")
    fun getUserNotes(): Flow<List<Note>>

    //    Update
    @Update
    suspend fun update(note: Note)

    //    Delete
    @Delete
    suspend fun deleteUserNote(note: Note)
}