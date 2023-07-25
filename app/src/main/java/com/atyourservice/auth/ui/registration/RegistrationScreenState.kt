package com.atyourservice.auth.ui.registration

import com.atyourservice.core.utils.ErrorType

sealed class RegistrationScreenState {
    object Loading : RegistrationScreenState()
    object SignedIn : RegistrationScreenState()
    data class Error(val errorType: ErrorType) : RegistrationScreenState()
}