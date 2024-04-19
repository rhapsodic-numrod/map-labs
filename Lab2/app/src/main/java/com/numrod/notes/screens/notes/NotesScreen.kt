package com.numrod.notes.screens.notes

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.numrod.notes.Screens
import com.numrod.notes.data.models.Note
import com.numrod.notes.ui.theme.NotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navController: NavController,
    noteList: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    onLogout: () -> Unit
) {

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(title = { Text(text = "NotesApp") }, actions = {
                ElevatedButton(onClick = {
                    onLogout()
                    navController.navigate(Screens.LoginScreen.route)
                }) {
Text(text = "LOGOUT")
                }
            })
        },

        ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            NoteForm(onAddNote)
            Divider(Modifier.height(2.dp))
            NotesList(noteList, onRemoveNote)
        }
    }
}


@Composable
fun NotesList(notes: List<Note>, onRemoveNote: (Note) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes) { note ->
            NoteCard(note, onRemoveNote)
        }
    }
}

@Composable
fun NoteCard(note: Note, onRemoveNote: (Note) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = note.title, fontWeight = FontWeight.Bold)
                Text(text = note.description)
                Text(text = note.dateCreated.toString())
            }
            IconButton(onClick = { onRemoveNote(note) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}


@Composable
fun NoteForm(onAddNote: (Note) -> Unit) {
    var title by rememberSaveable {
        mutableStateOf("")
    }

    var description by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { text ->
                title = text
            },
            label = { Text(text = "Title") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        TextField(
            value = description,
            onValueChange = { text ->
                description = text
            },
            label = { Text(text = "Description") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Button(onClick = {
            if (title.isNotEmpty() && description.isNotEmpty()) {
                onAddNote(Note(title = title, description = description))

//               clear fields
                title = ""
                description = ""

//                Todo: Show toast
                Toast.makeText(context, "Note addad sucessfully", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Save")
        }
    }
}

@Preview
@Composable
private fun NotesScreenPreview() {
    NotesTheme {
//        NotesScreen()
    }
}

