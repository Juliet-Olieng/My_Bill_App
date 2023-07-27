package com.julietolieng.bills_app.ui.repository

import com.julietolieng.bills_app.ui.api.ApiClient
import com.julietolieng.bills_app.ui.api.ApiInterface
import com.julietolieng.bills_app.ui.model.RegisterRequest
import com.julietolieng.bills_app.ui.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }

    }
}