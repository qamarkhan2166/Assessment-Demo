package com.example.mvvmdemoproject.di.repository

import com.example.mvvmdemoproject.data.remote.ApiService
import com.example.mvvmdemoproject.data.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object LoginRepositoryModule {

    @Provides
    fun providesLoginRepository(apiService: ApiService):
        LoginRepository = LoginRepository(apiService)
}
