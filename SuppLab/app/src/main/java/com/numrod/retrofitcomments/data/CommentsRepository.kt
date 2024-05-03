package com.numrod.retrofitcomments.data

import com.numrod.retrofitcomments.data.model.Comment
import retrofit2.Response

class CommentsRepository(
    private val commentsApi: CommentService = getRetrofitClient()
) {
    suspend fun getComments(): Response<List<Comment>>{
        return commentsApi.getComments()
    }
}