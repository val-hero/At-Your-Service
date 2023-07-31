package com.atyourservice.user.data

import android.util.Log
import com.atyourservice.User
import com.atyourservice.core.utils.ErrorType
import com.atyourservice.core.utils.TaskResult
import com.atyourservice.user.domain.UserRepository
import com.atyourservice.user.model.DatabaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val COLLECTION_USER = "USER"
const val UID = "id"
const val EMAIL = "email"
const val FIRSTNAME = "firstName"
const val LASTNAME = "lastName"

class UserRepositoryImpl(private val databaseClient: FirebaseFirestore) : UserRepository {

    lateinit var userUid: String

    override suspend fun saveUser(user: User) {
        //maybe null
        this.userUid = user.id!!
        withContext(Dispatchers.IO) {
            databaseClient.collection(COLLECTION_USER).document(user.id).set(user).await()
        }
    }

    override suspend fun getUser(): TaskResult<DatabaseUser> {
        return try {
            withContext(Dispatchers.IO) {
                val user =
                    databaseClient.collection(COLLECTION_USER).document(userUid).get().await()
                        .toObject(DatabaseUser::class.java)
                Log.d("tag", "Получили")
                TaskResult.Success(user)
            }
        } catch (e: Exception) {
            Log.d("tag", "Не получили" + e.stackTraceToString())
            TaskResult.Error(ErrorType.Unknown)
        }
    }

    override suspend fun deleteUser(): TaskResult<Boolean> {
        return try {
            withContext(Dispatchers.IO) {
                val user = databaseClient.collection(COLLECTION_USER).document(userUid)
                val deleteField = hashMapOf<String, Any>(
                    UID to FieldValue.delete(),
                    EMAIL to FieldValue.delete(),
                    FIRSTNAME to FieldValue.delete(),
                    LASTNAME to FieldValue.delete()
                )
                user.update(deleteField)
                databaseClient.collection(COLLECTION_USER).document(userUid).delete()
                Log.d("tag", "Удалено с базы")
                TaskResult.Success(true)
            }
        } catch (e: Exception) {
            Log.d("tag", "Неудалено с базы. Ошибка" + e.stackTraceToString())
            TaskResult.Error(ErrorType.Unknown)
        }
    }
}