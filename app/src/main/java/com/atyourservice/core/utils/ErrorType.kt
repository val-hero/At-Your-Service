package com.atyourservice.core.utils

import com.example.atyourservice.R

sealed class ErrorType {
    data class Auth(val message: String) : ErrorType()
    object Unknown : ErrorType()

    // сюда нужно добавлять любые виды ошибок, которые хотим обработать
    // параметры не обязательны, по необходимости
}

data class Details(
    val code: Int? = null,
    val message: String? = null,
    val stringResourceId: Int? = null
)

fun ErrorType.getDetails(): Details = when (this) {
    is ErrorType.Auth -> Details(message = message)
    else -> Details(stringResourceId = R.string.unknown_error)
}
