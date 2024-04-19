package com.numrod.notes.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numrod.notes.data.SharedPrefsManager
import com.numrod.notes.data.models.User
import com.numrod.notes.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val myPrefsManager: SharedPrefsManager
) : ViewModel() {

    fun login(user: User): Int {
        var result = 0
        viewModelScope.launch {
            val res = repository.login(user)
            if (res > 0) {
                myPrefsManager.saveData("username", user.username)
            }
            result = res
        }
        return result
    }
}