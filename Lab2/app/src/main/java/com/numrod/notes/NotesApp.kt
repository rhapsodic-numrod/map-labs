package com.numrod.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.numrod.notes.data.SharedPrefsManager
import com.numrod.notes.screens.auth.login.LoginScreen
import com.numrod.notes.screens.auth.login.LoginViewModel
import com.numrod.notes.screens.auth.registration.RegistrationScreen
import com.numrod.notes.screens.auth.registration.RegistrationViewModel
import com.numrod.notes.screens.notes.NoteViewModel
import com.numrod.notes.screens.notes.NotesScreen

enum class Routes {
    LOGIN, REGISTER, HOME,
}

sealed class Screens(val route: String) {
    data object LoginScreen : Screens(Routes.LOGIN.name)
    data object RegisterScreen : Screens(Routes.REGISTER.name)
    data object HomeScreen : Screens(Routes.HOME.name)
}

@Composable
fun NotesApp(
    navController: NavHostController,
    noteViewModel: NoteViewModel,
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel,
    myPrefsManager: SharedPrefsManager
) {
    val notesList = noteViewModel.userNoteList.collectAsState().value

    NavHost(navController = navController, startDestination = Routes.LOGIN.name) {
        composable(route = Screens.HomeScreen.route) {
            NotesScreen(navController = navController, noteList = notesList, onAddNote = { note ->
                noteViewModel.addNote(note)
            }, onRemoveNote = { note ->
                noteViewModel.deleteNote(note)
            }) {
                myPrefsManager.deleteData("username")
            }
        }

        composable(route = Screens.RegisterScreen.route) {
            RegistrationScreen(navController, onRegister = { user ->
                registrationViewModel.register(user)
            })
        }

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController, viewModel = loginViewModel)
        }
    }
}
