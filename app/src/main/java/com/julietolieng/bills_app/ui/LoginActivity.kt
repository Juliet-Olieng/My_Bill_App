package com.julietolieng.bills_app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.julietolieng.bills_app.databinding.ActivityLoginBinding
import com.julietolieng.bills_app.ui.model.LoginRequest
import com.julietolieng.bills_app.ui.viewModel.LoginViewModel

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val loginnViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        onResume()
    }

    override fun onResume() {
        super.onResume()
        validateLogin()
    }

    fun validateLogin(){
        val email=binding.etEmail.text.toString()
        val password = binding.tiePasswordtwo.text.toString()
        var error = false

        if (email.isEmpty()) {
            binding.etEmail.error = "Username is required"
            error = true

        }

        if (password.isEmpty()) {
            binding.tiePasswordtwo.error = "Password is required"
            error = true


        }
        if (!error) {
            val loginRequest= LoginRequest(
                password=password,
                email = email,
            )

            loginnViewModel.loginUser(loginRequest)
        }



        Toast.makeText(this, "login succesfully", Toast.LENGTH_SHORT).show()
    }


}