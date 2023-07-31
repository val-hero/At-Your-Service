package com.atyourservice.user.domain.usecase

import com.atyourservice.User
import com.atyourservice.user.domain.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.saveUser(user)
}