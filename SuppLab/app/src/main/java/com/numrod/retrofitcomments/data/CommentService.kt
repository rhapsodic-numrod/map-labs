package com.numrod.retrofitcomments.data

import com.numrod.retrofitcomments.data.model.Comment
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

fun getRetrofitClient(): CommentService {
    // Create or client
    val client = Retrofit.Builder()
        .baseUrl(BASE_URL) // Specify your base URL
        .addConverterFactory(GsonConverterFactory.create()) // Specify JSon convertion method
        .client(OkHttpClient())// Add converter factory for Gson
        .build()
    return client.create(CommentService::class.java)
}
interface CommentService {
    @GET("comments")
    suspend fun getComments(): Response<List<Comment>>
}