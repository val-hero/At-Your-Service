package com.atyourservice.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atyourservice.core.utils.TaskResult
import com.atyourservice.user.domain.usecase.DeleteUserUseCase
import com.atyourservice.user.domain.usecase.GetUserUseCase
import com.atyourservice.user.model.DatabaseUser
import kotlinx.coroutines.launch

class SearchHostViewModel(
    private val getUser: GetUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _userData = MutableLiveData(DatabaseUser())
    val userData: LiveData<DatabaseUser> = _userData

    fun getInfo() = viewModelScope.launch {
        when (val result = getUser()) {
            is TaskResult.Success -> {
                _userData.value = result.data
            }

            is TaskResult.Error -> {

            }
        }
    }

    fun delete() {
        viewModelScope.launch { deleteUserUseCase() }
    }
}