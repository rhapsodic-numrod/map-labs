package com.numrod.notes

import android.app.Application
import com.numrod.notes.data.db.NotesDatabase
import com.numrod.notes.data.repository.NoteRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication: Application() {
}