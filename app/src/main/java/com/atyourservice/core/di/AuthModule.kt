package com.atyourservice.core.di

import com.atyourservice.auth.data.repository.AuthRepositoryImpl
import com.atyourservice.auth.data.repository.UserBaseRepositoryImpl
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.auth.domain.repository.UserBaseRepository
import com.atyourservice.auth.domain.usecase.DeleteInfoUserUseCase
import com.atyourservice.auth.domain.usecase.GetInfoUserUseCase
import com.atyourservice.auth.domain.usecase.LogOutUseCase
import com.atyourservice.auth.domain.usecase.ResetPasswordUseCase
import com.atyourservice.auth.domain.usecase.SaveUserUseCase
import com.atyourservice.auth.domain.usecase.SignInUseCase
import com.atyourservice.auth.domain.usecase.SignUpUseCase
import com.atyourservice.auth.ui.authorization.AuthorizationViewModel
import com.atyourservice.auth.ui.registration.RegistrationViewModel
import com.atyourservice.auth.ui.resetpassword.ResetPassViewModel
import com.atyourservice.search.ui.SearchHostViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(signUp = get(), saveUser = get(), logOut = get())
    }

    viewModel<AuthorizationViewModel>() {
        AuthorizationViewModel(signIn = get())
    }

    viewModel<ResetPassViewModel>() {
        ResetPassViewModel(resetPassword = get())
    }

    viewModel<SearchHostViewModel>() {
        SearchHostViewModel(getInfoUser = get(), deleteInfoUserUseCase = get())
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
    factory<SaveUserUseCase> {
        SaveUserUseCase(repository = get())
    }
    factory<GetInfoUserUseCase> {
        GetInfoUserUseCase(repository = get())
    }
    factory<DeleteInfoUserUseCase> {
        DeleteInfoUserUseCase(repository = get())
    }
    factory<LogOutUseCase> {
        LogOutUseCase(repository = get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(authClient = get())
    }
    single<UserBaseRepository> {
        UserBaseRepositoryImpl(authClient = get(), baseClient = get())
    }

    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
}