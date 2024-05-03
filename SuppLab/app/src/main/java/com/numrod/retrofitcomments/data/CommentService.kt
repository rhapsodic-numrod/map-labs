package com.numrod.retrofitcomments.data

import retrofit2.Response
import retrofit2.http.GET


private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    
interface CommentService {
    @GET
    suspend fun getComments(): Response<CommentModel>
}