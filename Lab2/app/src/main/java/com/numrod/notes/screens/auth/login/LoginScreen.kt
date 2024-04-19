package com.numrod.notes.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.numrod.notes.Routes
import com.numrod.notes.data.models.User
import com.numrod.notes.ui.theme.NotesTheme

@Composable
fun LoginScreen(
    navController: NavController,
//    onLogin: (User) -> Unit,
    viewModel: LoginViewModel
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }


    val context = LocalContext.current


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,) {
            Text(text = "Welcome Back", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(text = "Username")},
                    singleLine = true,
                    leadingIcon = { Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )},
                    keyboardOptions = KeyboardOptions( imeAction = ImeAction.Next),
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
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically){
                Button(onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()){
                        val newUser = User(username, password)
                        val res = viewModel.login(newUser)

                        if (res > 0) {
                    //                            Clear fields
                                username = ""
                                password = ""
                                Toast.makeText(context, "Welcome back, ${newUser.username}", Toast.LENGTH_SHORT).show()
                                navController.navigate(Routes.HOME.name)
                            } else{
                                Toast.makeText(context, "Incorrect Username/Password entered", Toast.LENGTH_SHORT).show()
                            }
                    }
                }) {
                    Text(text = "Login")
                }
                Text(text = "or")
                ElevatedButton(onClick = {
                        navController.navigate(Routes.REGISTER.name)
                }) {
                    Text(text = "Create Account")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    NotesTheme {
//        LoginScreen(rememberNavController())
    }
}