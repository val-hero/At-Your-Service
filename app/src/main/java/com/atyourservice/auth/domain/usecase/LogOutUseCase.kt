package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.AuthRepository

class LogOutUseCase(private val repository: AuthRepository) {

    operator fun invoke() = repository.logOut()
}