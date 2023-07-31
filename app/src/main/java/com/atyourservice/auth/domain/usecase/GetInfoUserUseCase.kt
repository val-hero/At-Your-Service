package com.atyourservice.auth.domain.usecase

import com.atyourservice.auth.domain.repository.UserBaseRepository
import com.atyourservice.auth.model.UserFirebase
import com.atyourservice.core.utils.TaskResult

class GetInfoUserUseCase(private val repository: UserBaseRepository) {

    suspend operator fun invoke(): TaskResult<UserFirebase> {
        return repository.getInfoUser()
    }

}