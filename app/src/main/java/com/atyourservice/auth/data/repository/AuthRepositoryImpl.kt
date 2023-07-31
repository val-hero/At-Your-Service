package com.atyourservice.auth.data.repository

import com.atyourservice.User
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.core.utils.ErrorType
import com.atyourservice.core.utils.TaskResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(private val authClient: FirebaseAuth) : AuthRepository {

    override suspend fun signUp(email: String, password: String): TaskResult<Boolean> {
        if (authClient.currentUser != null) return TaskResult.Error(ErrorType.AlreadySignedIn)

        return try {
            withContext(Dispatchers.IO) {
                authClient.createUserWithEmailAndPassword(email, password).await()
                TaskResult.Success(true)
            }
        } catch (e: Exception) {
            TaskResult.Error(getAuthError(e.message))
        }
    }

    override suspend fun signIn(email: String, password: String): TaskResult<Boolean> {
        if (authClient.currentUser != null) return TaskResult.Error(ErrorType.AlreadySignedIn)

        return try {
            withContext(Dispatchers.IO) {
                authClient.signInWithEmailAndPassword(email, password).await()
                TaskResult.Success(true)
            }
        } catch (e: Exception) {
            TaskResult.Error(getAuthError(e.message))
        }
    }

    override suspend fun sendNewPassword(email: String): TaskResult<Boolean> {
        return try {
            withContext(Dispatchers.IO) {
                authClient.sendPasswordResetEmail(email).await()
                TaskResult.Success(true)
            }
        } catch (e: Exception) {
            TaskResult.Error(getAuthError(e.message))
        }
    }

    override fun logOut() {
        authClient.signOut()
    }

    override suspend fun getCurrentUid(email: String, firstName: String, lastName: String): User {
        return withContext(Dispatchers.IO) {
            User(authClient.currentUser!!.uid, email, firstName, lastName)
        }
    }

    private fun getAuthError(message: String?): ErrorType {
        return message?.let {
            ErrorType.AuthFailed(message)
        } ?: ErrorType.Unknown
    }
}
