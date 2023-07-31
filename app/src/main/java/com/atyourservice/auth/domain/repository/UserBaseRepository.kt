package com.atyourservice.auth.domain.repository

import com.atyourservice.auth.model.UserFirebase
import com.atyourservice.core.utils.TaskResult

interface UserBaseRepository {
    suspend fun saveUser(email: String, firstName: String, lastName: String)
    suspend fun getInfoUser(): TaskResult<UserFirebase>
    suspend fun deleteInfoUser(): TaskResult<String>
}