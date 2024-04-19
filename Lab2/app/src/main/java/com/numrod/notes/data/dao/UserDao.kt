package com.numrod.notes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.numrod.notes.data.models.User

@Dao
interface UserDao {
//    register user
    @Insert
    suspend fun insertUser(user: User)
//    login
//    get the count of usernames where the username AND password are the same as the entered data
//    if the count is 0 then no user exist
    @Query("SELECT COUNT(username) FROM users WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): Int
}