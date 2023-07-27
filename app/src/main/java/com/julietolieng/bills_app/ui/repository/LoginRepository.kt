package com.julietolieng.bills_app.ui.repository

import com.julietolieng.bills_app.ui.api.ApiClient
import com.julietolieng.bills_app.ui.api.ApiInterface
import com.julietolieng.bills_app.ui.model.LoginRequest
import com.julietolieng.bills_app.ui.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    private val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun login(loginRequest: LoginRequest) {
        return withContext(Dispatchers.IO) {
            apiClient.loginUser(loginRequest)
        }
    }
}
