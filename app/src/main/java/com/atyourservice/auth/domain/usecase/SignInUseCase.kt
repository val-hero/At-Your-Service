package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.AuthRepository

class SignInUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = repository.signIn(email, password)
}