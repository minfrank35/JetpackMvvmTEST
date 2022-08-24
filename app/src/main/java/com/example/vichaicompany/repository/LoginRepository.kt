package com.example.vichaicompany.repository

import com.example.vichaicompany.request.REQ_RefreshToken
import com.example.vichaicompany.response.RES_Login
import com.example.vichaicompany.response.RES_UserInfo


interface LoginRepository {
    suspend fun getLoginData() : RES_Login

    suspend fun getUserInfo(accessToken : String) : RES_UserInfo

    suspend fun refreshToken(refreshToken : REQ_RefreshToken) : RES_Login

}