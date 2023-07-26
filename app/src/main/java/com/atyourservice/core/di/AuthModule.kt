package com.atyourservice.core.di

import com.atyourservice.auth.data.repository.AuthRepositoryImpl
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.auth.ui.authorization.AuthorizationViewModel
import com.atyourservice.auth.ui.registration.RegistrationViewModel
import com.atyourservice.auth.ui.resetpassword.ResetPassViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(repository = get())
    }

    viewModel<AuthorizationViewModel>() {
        AuthorizationViewModel(repository = get())
    }

    viewModel<ResetPassViewModel>() {
        ResetPassViewModel(repository = get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(authClient = get())
    }

    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
}