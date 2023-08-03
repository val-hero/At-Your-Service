package com.atyourservice.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.usecase.GetAuthStateUseCase

class HostActivityViewModel(private val getAuthStateUseCase: GetAuthStateUseCase) : ViewModel() {

    private var viewModelIsReady = false
    private var firstEmit = true

    fun getAuthState() = liveData<Boolean> {
        getAuthStateUseCase(viewModelScope).collect { state ->
            emit(state)

            if (!firstEmit) {
                viewModelIsReady = true
            }

            firstEmit = false
        }
    }

    fun getViewModelReadyState() = viewModelIsReady
}