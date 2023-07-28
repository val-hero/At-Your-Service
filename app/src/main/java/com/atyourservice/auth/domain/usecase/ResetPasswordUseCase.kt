package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.AuthRepository

class ResetPasswordUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String) = repository.sendNewPassword(email)
}