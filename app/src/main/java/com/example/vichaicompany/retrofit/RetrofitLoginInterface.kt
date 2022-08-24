package com.example.covid_19_for_android.retrofit

import com.example.vichaicompany.response.RES_Login
import com.example.vichaicompany.request.REQ_Login
import com.example.vichaicompany.request.REQ_RefreshToken
import com.example.vichaicompany.response.RES_UserInfo
import retrofit2.Call
import retrofit2.http.*

interface RetrofitLoginInterface {
    @POST("api/login/")
    fun getLoginData(@Body req : REQ_Login) :  Call<RES_Login>

    @GET("api/refresh-token/")
    fun getRefreshToken(@Body req : REQ_RefreshToken) : Call<RES_Login>


    @GET("api/user-info/")
    fun getUserInfo(
            @Header("Authentication") authentication : String
        ) : Call<RES_UserInfo>

    /*


       "api/login/",
       -> accept_token : 30min(timeout)
       -> refresh_token:

       @POST
       HTTP Header : "Authentication" : "HolyToken accept_token"
       "api/refresh-token/", every 29min

       Request
       {
           "refresh": refresh_token
       }

       Response
       {
           accept_token
           refresh_token
       }

        @GET
        + "api/user-info/"
        HTTP Header : "Authentication" : "HolyToken accept_token"



        response
        {username,email}

        @GET
        "api/users/" : Admin
        HTTP Header : "Authentication" : "HolyToken accept_token"
        vichhai, 1234

        [
            {},
            {}
        ]

        @POST
        "api/register" : Normal User
        {
            "username",
            "email",
            "password"
        }
    * */
}