package com.atyourservice.core.di

import com.atyourservice.auth.data.repository.AuthRepositoryImpl
import com.atyourservice.auth.domain.repository.AuthRepository
import com.atyourservice.auth.ui.registration.RegistrationFragmentVM
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<RegistrationFragmentVM> {
        RegistrationFragmentVM(repository = get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(authClient = get())
    }

    single<FirebaseAuth> {
        FirebaseAuth.getInstance()
    }
}