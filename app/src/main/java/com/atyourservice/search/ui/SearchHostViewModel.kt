package com.atyourservice.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atyourservice.auth.domain.usecase.DeleteInfoUserUseCase
import com.atyourservice.auth.domain.usecase.GetInfoUserUseCase
import com.atyourservice.auth.model.UserFirebase
import com.atyourservice.core.utils.TaskResult
import kotlinx.coroutines.launch

class SearchHostViewModel(
    private val getInfoUser: GetInfoUserUseCase,
    private val deleteInfoUserUseCase: DeleteInfoUserUseCase
) : ViewModel() {

    private val _userData = MutableLiveData(UserFirebase())
    val userData: LiveData<UserFirebase> = _userData

    fun getInfo() = viewModelScope.launch {
        when (val result = getInfoUser.invoke()) {
            is TaskResult.Success -> {
                _userData.value = result.data
            }

            is TaskResult.Error -> {

            }
        }
    }

    fun delete(){
        viewModelScope.launch { deleteInfoUserUseCase.invoke() }

    }

}