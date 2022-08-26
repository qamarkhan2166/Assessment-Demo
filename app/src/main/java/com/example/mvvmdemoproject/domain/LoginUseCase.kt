package com.example.mvvmdemoproject.domain

import com.example.mvvmdemoproject.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
)
