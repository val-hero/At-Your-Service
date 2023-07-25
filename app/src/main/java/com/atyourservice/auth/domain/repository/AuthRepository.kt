package com.atyourservice.auth.domain.repository

import com.atyourservice.core.utils.TaskResult

interface AuthRepository {
    suspend fun signUp(email: String, password: String): TaskResult<Boolean>

    suspend fun signIn(email: String, password: String): TaskResult<Boolean>

    suspend fun sendNewPassword(email: String): TaskResult<Boolean>

    fun logOut()
}