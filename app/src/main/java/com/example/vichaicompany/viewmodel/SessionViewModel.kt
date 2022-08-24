package com.example.vichaicompany.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vichaicompany.repository.LoginRepositoryImpl
import com.example.vichaicompany.request.REQ_RefreshToken
import com.example.vichaicompany.response.RES_Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SessionViewModel : ViewModel(){
    private val _accessToken = MutableLiveData<String>()
    val accessToken : LiveData<String> get() = _accessToken

    private val _refreshToken = MutableLiveData<String>()
    val refreshToken : LiveData<String> get() = _refreshToken


    fun requestLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            val res : RES_Login = LoginRepositoryImpl().getLoginData()
            _accessToken.postValue(res.access)
            _refreshToken.postValue(res.refresh)
        }
    }

    fun refreshLoginToken() {
        CoroutineScope(Dispatchers.IO).launch {
            val res : RES_Login = LoginRepositoryImpl().refreshToken(REQ_RefreshToken(refreshToken.value as String))
            _accessToken.postValue(res.access)
            _refreshToken.postValue(res.refresh)
        }
    }

}