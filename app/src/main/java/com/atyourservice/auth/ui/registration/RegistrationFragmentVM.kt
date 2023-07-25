package com.atyourservice.auth.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.core.utils.TaskResult.Error
import com.atyourservice.core.utils.TaskResult.Success
import kotlinx.coroutines.launch

class RegistrationFragmentVM(
    private val repository: AuthRepository,
) : ViewModel() {

    var screenState = MutableLiveData<RegistrationScreenState>()
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        screenState.value = RegistrationScreenState.Loading

        val authResult = repository.signUp(email, password)
        when (authResult) {
            is Success -> screenState.postValue(RegistrationScreenState.SignedIn)
            is Error -> screenState.postValue(RegistrationScreenState.Error(authResult.errorType))
        }
    }
}