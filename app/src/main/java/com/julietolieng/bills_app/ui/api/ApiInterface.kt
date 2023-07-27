package com.julietolieng.bills_app.ui.api


import com.julietolieng.bills_app.ui.model.LoginRequest
import com.julietolieng.bills_app.ui.model.LoginResponse
import com.julietolieng.bills_app.ui.model.RegisterRequest
import com.julietolieng.bills_app.ui.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST ("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest):Response<LoginResponse>
}