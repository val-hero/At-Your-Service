package com.atyourservice.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.usecase.GetAuthStateUseCase

class HostActivityViewModel(private val getAuthStateUseCase: GetAuthStateUseCase) : ViewModel() {

    fun getAuthState() = liveData<Boolean> {
        getAuthStateUseCase(viewModelScope).collect { state ->
            emit(state)
        }
    }
}