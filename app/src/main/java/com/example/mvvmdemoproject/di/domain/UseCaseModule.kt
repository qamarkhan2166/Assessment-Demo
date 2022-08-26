package com.example.mvvmdemoproject.di.domain

import com.example.mvvmdemoproject.data.repository.LoginRepository
import com.example.mvvmdemoproject.domain.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object LoginUseCaseModule {

    @Provides
    fun providesLoginUseCase(
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCase(loginRepository)
    }
}
