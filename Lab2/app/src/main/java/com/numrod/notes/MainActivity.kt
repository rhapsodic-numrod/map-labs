package com.numrod.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.numrod.notes.data.SharedPrefsManager
import com.numrod.notes.screens.auth.login.LoginViewModel
import com.numrod.notes.screens.auth.registration.RegistrationViewModel
import com.numrod.notes.screens.notes.NoteViewModel
import com.numrod.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: SharedPrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val noteViewModel = viewModel<NoteViewModel>()
                val loginViewModel = viewModel<LoginViewModel>()
                val registrationViewModel = viewModel<RegistrationViewModel>()
                NotesApp(
                    navController = navController,
                    noteViewModel = noteViewModel,
                    loginViewModel = loginViewModel,
                    registrationViewModel = registrationViewModel,
                    myPrefsManager = preferencesManager
                )
            }
        }
    }
}