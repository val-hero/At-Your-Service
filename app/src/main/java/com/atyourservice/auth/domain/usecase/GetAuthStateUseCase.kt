package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope

class GetAuthStateUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(coroutineScope: CoroutineScope) = authRepository.getAuthState(coroutineScope)
}