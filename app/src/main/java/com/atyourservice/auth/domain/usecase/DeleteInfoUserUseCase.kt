package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.UserBaseRepository
import com.atyourservice.core.utils.TaskResult

class DeleteInfoUserUseCase(private val repository: UserBaseRepository) {

    suspend operator fun invoke(): TaskResult<String> {
        return repository.deleteInfoUser()
    }

}