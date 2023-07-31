package com.atyourservice.core.di

import com.atyourservice.auth.data.repository.AuthRepositoryImpl
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.auth.domain.usecase.GetCurrentUserIdUseCase
import com.atyourservice.auth.domain.usecase.LogOutUseCase
import com.atyourservice.auth.domain.usecase.ResetPasswordUseCase
import com.atyourservice.auth.domain.usecase.SignInUseCase
import com.atyourservice.auth.domain.usecase.SignUpUseCase
import com.atyourservice.auth.ui.authorization.AuthorizationViewModel
import com.atyourservice.auth.ui.registration.RegistrationViewModel
import com.atyourservice.auth.ui.resetpassword.ResetPassViewModel
import com.atyourservice.search.ui.SearchHostViewModel
import com.atyourservice.user.data.UserRepositoryImpl
import com.atyourservice.user.domain.UserRepository
import com.atyourservice.user.domain.usecase.DeleteUserUseCase
import com.atyourservice.user.domain.usecase.GetUserUseCase
import com.atyourservice.user.domain.usecase.SaveUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(signUp = get(), saveUser = get(), getCurrentUserId = get())
    }

    viewModel<AuthorizationViewModel>() {
        AuthorizationViewModel(signIn = get())
    }

    viewModel<ResetPassViewModel>() {
        ResetPassViewModel(resetPassword = get())
    }

    viewModel<SearchHostViewModel>() {
        SearchHostViewModel(getUser = get(), deleteUserUseCase = get())
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
    factory<GetCurrentUserIdUseCase> {
        GetCurrentUserIdUseCase(repository = get())
    }
    factory<SaveUserUseCase> {
        SaveUserUseCase(userRepository = get())
    }
    factory<GetUserUseCase> {
        GetUserUseCase(repository = get())
    }
    factory<DeleteUserUseCase> {
        DeleteUserUseCase(repository = get())
    }
    factory<LogOutUseCase> {
        LogOutUseCase(repository = get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(authClient = get())
    }
    single<UserRepository> {
        UserRepositoryImpl(databaseClient = get())
    }

    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
}
