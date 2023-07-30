package com.atyourservice.core.di

import com.atyourservice.auth.data.repository.AuthRepositoryImpl
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.auth.domain.usecase.GetAuthStateUseCase
import com.atyourservice.auth.domain.usecase.ResetPasswordUseCase
import com.atyourservice.auth.domain.usecase.SignInUseCase
import com.atyourservice.auth.domain.usecase.SignUpUseCase
import com.atyourservice.auth.ui.authorization.AuthorizationViewModel
import com.atyourservice.auth.ui.registration.RegistrationViewModel
import com.atyourservice.auth.ui.resetpassword.ResetPassViewModel
import com.atyourservice.core.ui.HostActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(signUp = get())
    }

    viewModel<AuthorizationViewModel>() {
        AuthorizationViewModel(signIn = get())
    }

    viewModel<ResetPassViewModel>() {
        ResetPassViewModel(resetPassword = get())
    }

    viewModel<HostActivityViewModel> {
        HostActivityViewModel(getAuthStateUseCase = get())
    }

    factory {
        GetAuthStateUseCase(authRepository = get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(repository = get())
    }

    factory<SignInUseCase> {
        SignInUseCase(repository = get())
    }

    factory<ResetPasswordUseCase> {
        ResetPasswordUseCase(repository = get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(authClient = get())
    }

    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
}