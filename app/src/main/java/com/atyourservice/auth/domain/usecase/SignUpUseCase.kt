package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = repository.signUp(email, password)
}