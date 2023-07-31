package com.atyourservice.auth.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.usecase.GetCurrentUserIdUseCase
import com.atyourservice.auth.domain.usecase.SignUpUseCase
import com.atyourservice.auth.ui.AuthFlowScreenState
import com.atyourservice.core.utils.TaskResult
import com.atyourservice.user.domain.usecase.SaveUserUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val signUp: SignUpUseCase,
    private val saveUser: SaveUserUseCase,
    private val getCurrentUserId: GetCurrentUserIdUseCase
) : ViewModel() {

    private val _screenState = MutableLiveData<AuthFlowScreenState>()
    val screenState: MutableLiveData<AuthFlowScreenState> = _screenState

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) = viewModelScope.launch {
        _screenState.value = AuthFlowScreenState.Loading

        when (val authResult = signUp(email, password)) {
            is TaskResult.Success -> {
                _screenState.postValue(AuthFlowScreenState.Success)

                val userRegistration = getCurrentUserId(email, firstName, lastName)
                saveUser(userRegistration)
            }

            is TaskResult.Error -> _screenState.postValue(AuthFlowScreenState.Error(authResult.errorType))
        }
    }
}