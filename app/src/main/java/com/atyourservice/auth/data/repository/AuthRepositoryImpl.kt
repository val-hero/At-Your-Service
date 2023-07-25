package com.atyourservice.auth.data.repository

import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.core.utils.ErrorType
import com.atyourservice.core.utils.TaskResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val authClient: FirebaseAuth) : AuthRepository {

    override suspend fun signUp(email: String, password: String): TaskResult<Boolean> {
        return try {
            authClient.createUserWithEmailAndPassword(email, password).await()
            TaskResult.Success(true)
        } catch (e: Exception) {
            TaskResult.Error(getAuthError(e.message))
        }
    }

    override suspend fun signIn(email: String, password: String): TaskResult<Boolean> {
        return try {
            authClient.signInWithEmailAndPassword(email, password).await()
            TaskResult.Success(true)
        } catch (e: Exception) {
            TaskResult.Error(getAuthError(e.message))
        }
    }

    override suspend fun sendNewPassword(email: String): TaskResult<Boolean> {
        return try {
            authClient.sendPasswordResetEmail(email).await()
            TaskResult.Success(true)
        } catch (e: Exception) {
            TaskResult.Error(getAuthError(e.message))
        }
    }

    override fun logOut() {
        authClient.signOut()
    }

    private fun getAuthError(message: String?): ErrorType {
        return message?.let {
            ErrorType.Auth(message)
        } ?: ErrorType.Unknown
    }
}
