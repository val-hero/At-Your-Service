package com.atyourservice.auth.data.datasource

import com.atyourservice.auth.data.datasource.remote.UserDto

interface UserRemoteDataSource {
    suspend fun get(): UserDto
}