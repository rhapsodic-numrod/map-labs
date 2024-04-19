package com.numrod.notes.data.di

import android.content.Context
import androidx.room.Room
import com.numrod.notes.data.SharedPrefsManager
import com.numrod.notes.data.dao.NoteDao
import com.numrod.notes.data.dao.UserDao
import com.numrod.notes.data.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NoteDao = notesDatabase.noteDao()

    @Singleton
    @Provides
    fun provideUserDao(notesDatabase: NotesDatabase): UserDao = notesDatabase.userDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NotesDatabase =  Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        "notes_db",
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPrefsManager = SharedPrefsManager(context)
}