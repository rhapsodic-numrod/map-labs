package com.numrod.retrofitcomments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numrod.retrofitcomments.data.CommentsRepository
import com.numrod.retrofitcomments.data.model.Comment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentsAppViewModel(
    private val repository: CommentsRepository = CommentsRepository()
): ViewModel()  {
    private val _commentsList = MutableStateFlow<List<Comment>?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)

    val commentsList get() = _commentsList.asStateFlow()
    val errorMessage get() = _errorMessage.asStateFlow()
    val isLoading get() = _isLoading.asStateFlow()

    fun getCommentsList(){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getComments()
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    Log.d("Success", "$body?.size")
                    _isLoading.value = false
                    _commentsList.value = body
                }
            } else{
                val error = response.errorBody()
                if (error != null) {
                    Log.d("Success", error.string())
                    _isLoading.value = false
                    _errorMessage.value = error.string()
                }
            }
        }
    }
}