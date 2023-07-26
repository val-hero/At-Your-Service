package com.atyourservice.auth.ui

import com.atyourservice.core.utils.ErrorType

sealed class AuthFlowScreenState {
    object Loading : AuthFlowScreenState()
    object Success : AuthFlowScreenState()
    data class Error(val errorType: ErrorType) : AuthFlowScreenState()
}