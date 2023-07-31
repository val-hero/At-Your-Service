package com.atyourservice.user.domain.usecase

import com.atyourservice.core.utils.TaskResult
import com.atyourservice.user.domain.UserRepository
import com.atyourservice.user.model.DatabaseUser

class GetUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(): TaskResult<DatabaseUser> {
        return repository.getUser()
    }

}