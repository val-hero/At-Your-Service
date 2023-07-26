package com.atyourservice.auth.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.auth.ui.AuthFlowScreenState
import com.atyourservice.core.utils.TaskResult
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _screenState = MutableLiveData<AuthFlowScreenState>()
    val screenState: MutableLiveData<AuthFlowScreenState> = _screenState

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        _screenState.value = AuthFlowScreenState.Loading

        val authResult = repository.signUp(email, password)
        when (authResult) {
            is TaskResult.Success -> _screenState.postValue(AuthFlowScreenState.Success)
            is TaskResult.Error -> _screenState.postValue(AuthFlowScreenState.Error(authResult.errorType))
        }
    }
}