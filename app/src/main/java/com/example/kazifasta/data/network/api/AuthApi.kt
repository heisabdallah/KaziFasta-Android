package com.example.kazifasta.data.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)

data class RegisterRequest(
    val fName: String,
    val lName: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
)

data class LoginResponse(val header: Header)

data class RegisterResponse(val header: Header)

interface AuthListener {
    fun onAuthSuccess(token: String)
    fun onAuthFailure(errorMessage: String)
}

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}

const val BASE_URL = "https://abdallahauth.vercel.app/api/user/"

val authRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val authApi: AuthApi = authRetrofit.create(AuthApi::class.java)