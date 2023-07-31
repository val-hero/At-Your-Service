package com.atyourservice.user.domain.usecase

import com.atyourservice.user.domain.UserRepository

class DeleteUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.deleteUser()

}