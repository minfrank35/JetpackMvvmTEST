package com.example.vichaicompany.repository

import android.util.Log
import com.example.covid_19_for_android.const.ApiConst
import com.example.covid_19_for_android.retrofit.RetrofitLoginInterface
import com.example.vichaicompany.response.RES_Login
import com.example.vichaicompany.request.REQ_Login
import com.example.vichaicompany.request.REQ_RefreshToken
import com.example.vichaicompany.response.RES_UserInfo
import com.google.gson.GsonBuilder
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRepositoryImpl : LoginRepository {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor { message ->
        Log.e(
            "minfrank2",
            message
        )
    }.apply {
        level = HttpLoggingInterceptor.Level.NONE
    }).addInterceptor {
        // Request
        val request = it.request()
            .newBuilder()
            .headers(
                Headers.headersOf(
                "Content-type", "application/json"
            ))
            .build()
        // Response
        val response = it.proceed(request)
        response
    }.build()

    private var gson = GsonBuilder().setLenient().create()

    private val covidRetrofit = Retrofit.Builder()
        .baseUrl(ApiConst.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val covidRetrofitService  = covidRetrofit.create(RetrofitLoginInterface::class.java)

    override suspend fun getLoginData(): RES_Login {
        val call = covidRetrofitService.getLoginData(REQ_Login("vichhai", "1234"))
        val response : Response<RES_Login> = call.execute()

        if(response.isSuccessful) return response.body() as RES_Login
        return RES_Login("err","err")
    }


    override suspend fun getUserInfo(accessToken : String): RES_UserInfo {
        val call = covidRetrofitService.getUserInfo(accessToken)
        val response : Response<RES_UserInfo> = call.execute()

        if(response.isSuccessful) return response.body() as RES_UserInfo
        return RES_UserInfo("err","err")
    }

    override suspend fun refreshToken(refreshToken : REQ_RefreshToken): RES_Login {
        val call = covidRetrofitService.getRefreshToken(refreshToken)
        val response : Response<RES_Login> = call.execute()

        if(response.isSuccessful) return response.body() as RES_Login
        return RES_Login("err","err")
    }
}