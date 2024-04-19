package com.numrod.notes.data.repository

import androidx.annotation.WorkerThread
import com.numrod.notes.data.dao.NoteDao
import com.numrod.notes.data.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun addNote(note: Note) = noteDao.insert(note)
    suspend fun updateNote(note: Note) = noteDao.update(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteUserNote(note)
    suspend fun getUserNotes(): Flow<List<Note>> = noteDao.getUserNotes().flowOn(Dispatchers.IO).conflate()

}