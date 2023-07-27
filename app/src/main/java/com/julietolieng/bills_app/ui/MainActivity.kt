package com.julietolieng.bills_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.julietolieng.bills_app.databinding.ActivityMainBinding
import com.julietolieng.bills_app.ui.model.RegisterRequest
import com.julietolieng.bills_app.ui.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            val intent= Intent(this, ActivityMainBinding::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener{
            validateSignUp()
        }

        userViewModel.regLiveData.observe(this,{
                regResponse -> Toast.makeText(this,regResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
            binding.pbRegister.visibility=View.GONE

        })

        userViewModel.errLiveData.observe(this,{
                err->Toast.makeText(this,err, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
            binding.pbRegister.visibility=View.GONE
        })


    }
    fun validateSignUp() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.tielLastName.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val email = binding.tieEmail.text.toString()
        val password = binding.tiePassword.text.toString()
        var error = false

        if (firstName.isEmpty()) {
            binding.tilFirstname.error = "Username is required"
            error = true

        }
        if (lastName.isEmpty()) {
            binding.tielLastName.error = "Username is required"
            error = true

        }

        if (phoneNumber.isEmpty()) {
            binding.tiePhoneNumber.error = "Phone number is required"
            error = true

        }

        if (email.isEmpty()) {
            binding.tieEmail.error = "Email is required"
            error = true

        }


        if (password.isEmpty()) {
            binding.tiePassword.error = "Password is required"
            error = true

        }
        if (!error) {
            val registerRequest = RegisterRequest(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                email = email,
                password = password

            )
            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }



    }