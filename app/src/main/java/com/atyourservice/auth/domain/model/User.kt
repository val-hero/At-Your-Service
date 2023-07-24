package com.atyourservice.auth.domain.model

data class User(
    val id: String,
    val email: String,
    val password: String,
//    val personalInfo: PersonalInfo?,
//    val expertInfo: ExpertInfo?,
//    val companyInfo: CompanyInfo?
)