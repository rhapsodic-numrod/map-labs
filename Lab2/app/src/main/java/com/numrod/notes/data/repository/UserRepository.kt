package com.numrod.notes.data.repository

import com.numrod.notes.data.dao.UserDao
import com.numrod.notes.data.models.User
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao){
    suspend fun createUser(user: User) = userDao.insertUser(user)

    suspend fun login(user: User) = runBlocking{
        val count = async { userDao.login(user.username, user.password) }
        count.start()
        count.await()
    }
}