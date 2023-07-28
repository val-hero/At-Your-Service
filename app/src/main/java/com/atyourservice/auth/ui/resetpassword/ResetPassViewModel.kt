package com.atyourservice.auth.ui.resetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.usecase.ResetPasswordUseCase
import com.atyourservice.auth.ui.AuthFlowScreenState
import com.atyourservice.core.utils.TaskResult
import kotlinx.coroutines.launch

class ResetPassViewModel(
    private val resetPassword: ResetPasswordUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<AuthFlowScreenState>()
    val screenState: MutableLiveData<AuthFlowScreenState> = _screenState

    fun sendNewPassword(email: String) = viewModelScope.launch {
        _screenState.value = AuthFlowScreenState.Loading

        val result = resetPassword(email)
        when (result) {
            is TaskResult.Success -> _screenState.postValue(AuthFlowScreenState.Success)
            is TaskResult.Error -> _screenState.postValue(AuthFlowScreenState.Error(result.errorType))
        }
    }
}