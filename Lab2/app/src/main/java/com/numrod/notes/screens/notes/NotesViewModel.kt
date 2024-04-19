package com.numrod.notes.screens.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.numrod.notes.data.models.Note
import com.numrod.notes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
    private val _userNoteList = MutableStateFlow<List<Note>>(emptyList())

    val userNoteList = _userNoteList.asStateFlow()

    init {
        viewModelScope.launch {
            Dispatchers.IO

            repository.getUserNotes().distinctUntilChanged().collect{
                listOfNotes ->
                if (listOfNotes.isEmpty()){
                    Log.d("Empty", ": Empty list")
                } else {
                    _userNoteList.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}

