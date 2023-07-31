package com.atyourservice.auth.data.repository

import android.util.Log
import com.atyourservice.auth.domain.repository.UserBaseRepository
import com.atyourservice.auth.model.UserFirebase
import com.atyourservice.core.utils.Constants.COLLECTION_USER
import com.atyourservice.core.utils.Constants.EMAIL
import com.atyourservice.core.utils.Constants.FIRSTNAME
import com.atyourservice.core.utils.Constants.LASTNAME
import com.atyourservice.core.utils.ErrorType
import com.atyourservice.core.utils.TaskResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserBaseRepositoryImpl(
    private val authClient: FirebaseAuth,
    private val baseClient: FirebaseFirestore
) : UserBaseRepository {
    private val uid = authClient.currentUser?.uid ?: ""

    override suspend fun saveUser(email: String, firstName: String, lastName: String) {
        withContext(Dispatchers.IO) {
            authClient.currentUser?.apply {
                val user = toUser(email, firstName, lastName)
                baseClient.collection(COLLECTION_USER).document(uid).set(user).await()
            }
        }
    }

    private fun toUser(email: String, firstName: String, lastName: String) = mapOf(
        EMAIL to email,
        FIRSTNAME to firstName,
        LASTNAME to lastName
    )


    override suspend fun getInfoUser(): TaskResult<UserFirebase> {
        return try {
            withContext(Dispatchers.IO) {
                val user = baseClient.collection(COLLECTION_USER).document(uid).get().await()
                    .toObject(UserFirebase::class.java)
                Log.d("tag", "Получили")
                TaskResult.Success(user)
            }
        } catch (e: Exception) {
            Log.d("tag", "Не получили" + e.stackTraceToString())
            TaskResult.Error(ErrorType.Unknown)
        }
    }

    override suspend fun deleteInfoUser(): TaskResult<String> {

        //Удаление с базы FirebaseAuth
        try {
            authClient.currentUser!!.delete()
            Log.d("tag", "Удалено с auth")
        } catch (e: Exception) {
            Log.d("tag", "Удаление с FirebaseAuth неудачно: " + e.stackTraceToString())
        }

        //Удаление с базы FirebaseFirestore
        return try {
            withContext(Dispatchers.IO) {
                val user = baseClient.collection(COLLECTION_USER).document(uid)
                val deleteField = hashMapOf<String, Any>(
                    EMAIL to FieldValue.delete(),
                    FIRSTNAME to FieldValue.delete(),
                    LASTNAME to FieldValue.delete()
                )
                user.update(deleteField)
                baseClient.collection(COLLECTION_USER).document(uid).delete()
                Log.d("tag", "Удалено с базы")
                TaskResult.Success("DELETED")
            }
        } catch (e: Exception) {
            Log.d("tag", "Неудалено с базы. Ошибка" + e.stackTraceToString())
            TaskResult.Error(ErrorType.Unknown)
        }
    }
}