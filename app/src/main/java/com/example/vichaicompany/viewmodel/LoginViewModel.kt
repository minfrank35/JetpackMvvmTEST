package com.example.vichaicompany.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.vichaicompany.response.RES_Login
import com.example.vichaicompany.repository.LoginRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepositoryImpl)  {
    var loginData : MutableState<RES_Login> = mutableStateOf(RES_Login("", ""))

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loginData.value = repository.getLoginData()
        }
    }

}