package com.atyourservice.auth.data.datasource.remote

import com.atyourservice.auth.data.datasource.UserRemoteDataSource

class FirebaseUserDataSource(): UserRemoteDataSource {
    override suspend fun get(): UserDto {
        TODO("Not yet implemented")
    }

}