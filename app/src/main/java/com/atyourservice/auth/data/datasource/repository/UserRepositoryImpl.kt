package com.atyourservice.auth.data.datasource.repository

import com.atyourservice.auth.data.datasource.UserRemoteDataSource
import com.atyourservice.auth.domain.model.User
import com.atyourservice.auth.domain.repository.UserRepository

class UserRepositoryImpl(dataSource: UserRemoteDataSource): UserRepository {
    override suspend fun get(): User {
        TODO("Not yet implemented")
    }
}