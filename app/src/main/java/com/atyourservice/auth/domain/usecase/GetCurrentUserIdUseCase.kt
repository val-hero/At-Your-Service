package com.atyourservice.auth.domain.usecase

import com.atyourservice.User
import com.atyourservice.auth.domain.repository.AuthRepository

class GetCurrentUserIdUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, firstName: String, lastName: String): User {
        return repository.getCurrentUid(email, firstName, lastName)
    }
}