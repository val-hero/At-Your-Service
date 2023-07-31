package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.UserBaseRepository

class SaveUserUseCase(private val repository: UserBaseRepository) {
    suspend operator fun invoke(email: String, firstName: String, lastName: String) =
        repository.saveUser(email, firstName, lastName)
}