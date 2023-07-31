package com.atyourservice.user.model

data class DatabaseUser(
    var id: String? = null,
    var email: String? = null,
    var firstName: String? = null,
    val lastName: String? = null
)
