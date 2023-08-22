package com.julietolieng.bills_app.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julietolieng.bills_app.ui.model.RegisterRequest
import com.julietolieng.bills_app.ui.model.RegisterResponse
import com.julietolieng.bills_app.ui.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    private val userRepo = UserRepository()
    val regLiveData= MutableLiveData<RegisterResponse>()
    val errLiveData= MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch{
            val response=userRepo.register(registerRequest)
            if (response.isSuccessful){
                regLiveData.postValue((response.body()))
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }

        }
    }
}