package com.numrod.notes.screens.auth.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numrod.notes.data.models.User
import com.numrod.notes.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {

    fun register(user: User) = viewModelScope.launch{
        repository.createUser(user)
    }
}