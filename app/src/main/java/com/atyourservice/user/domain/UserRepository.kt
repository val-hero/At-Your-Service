package com.atyourservice.user.domain

import com.atyourservice.User
import com.atyourservice.core.utils.TaskResult
import com.atyourservice.user.model.DatabaseUser

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(): TaskResult<DatabaseUser>
    suspend fun deleteUser(): TaskResult<Boolean>
}