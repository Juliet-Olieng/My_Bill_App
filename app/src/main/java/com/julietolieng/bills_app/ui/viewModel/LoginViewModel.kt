package com.julietolieng.bills_app.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julietolieng.bills_app.ui.model.LoginRequest
import com.julietolieng.bills_app.ui.model.LoginResponse
import com.julietolieng.bills_app.ui.repository.LoginRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val logRepo = LoginRepository()

    private val _logLiveData = MutableLiveData<LoginResponse?>()
    val logLiveData: MutableLiveData<LoginResponse?> = _logLiveData

    private val _errLiveData = MutableLiveData<String?>()
    val errLiveData: LiveData<String?> = _errLiveData

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = logRepo.login(loginRequest) as Response<LoginResponse>
            if (response.isSuccessful()) {
                val loginResponse = response.body()
                _logLiveData.postValue(loginResponse)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                _errLiveData.postValue(errorBody)
            }
        }
    }
}