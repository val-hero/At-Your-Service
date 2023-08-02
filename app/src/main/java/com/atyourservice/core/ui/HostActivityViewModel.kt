package com.atyourservice.core.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.usecase.GetAuthStateUseCase

class HostActivityViewModel(private val getAuthStateUseCase: GetAuthStateUseCase) : ViewModel() {

    private var vmIsReady = false
    private var flag = false

    fun getAuthState() = liveData<Boolean> {
        getAuthStateUseCase(viewModelScope).collect { state ->
            emit(state)
            if (flag) {
                vmIsReady = true
            }
            flag = true
        }
    }

    fun getVMState() = vmIsReady
}