package com.numrod.notes.screens.auth.registration

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.numrod.notes.Routes
import com.numrod.notes.data.models.User
import com.numrod.notes.ui.theme.NotesTheme
import java.sql.SQLException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    onRegister: (User) -> Unit
) {
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var username by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Create Account") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingVal ->
        Column(
            modifier = Modifier
                .padding(paddingVal)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Create New Account", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(value = username,
                    onValueChange = { username = it },
                    label = { Text(text = "Username") },
                    leadingIcon = { Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )},
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password")},
                    singleLine = true,
                    leadingIcon = { Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )},
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    try {

                        onRegister(User(username, password))
                    } catch (e : SQLException){
                        e.message?.let { Log.d("e", it) }
                    }

                    username = ""
                    password = ""

                    Toast.makeText(context, "Account created successfully. Login with your account details", Toast.LENGTH_LONG).show()
                    navController.navigate(Routes.LOGIN.name)
                }
            }) {
                Text(text = "Register")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    NotesTheme {
//        RegistrationScreen(rememberNavController())
    }
}