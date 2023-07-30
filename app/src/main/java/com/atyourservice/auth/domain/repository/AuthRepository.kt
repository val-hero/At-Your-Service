package com.atyourservice.auth.domain.repository

import com.atyourservice.core.utils.TaskResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(email: String, password: String): TaskResult<Boolean>

    suspend fun signIn(email: String, password: String): TaskResult<Boolean>

    suspend fun sendNewPassword(email: String): TaskResult<Boolean>

    fun getAuthState(coroutineScope: CoroutineScope): Flow<Boolean>

    fun logOut()
}