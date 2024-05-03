package com.numrod.retrofitcomments.data

data class CommentModel(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
