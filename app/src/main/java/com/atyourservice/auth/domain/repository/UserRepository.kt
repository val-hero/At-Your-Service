package com.atyourservice.auth.domain.repository

import com.atyourservice.auth.domain.model.User

interface UserRepository {
    suspend fun get(): User
}