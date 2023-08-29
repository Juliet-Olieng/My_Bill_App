package ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.julietolieng.bills_app.databinding.ActivityLoginBinding
import com.julietolieng.bills_app.ui.model.LoginRequest
import com.julietolieng.bills_app.ui.model.LoginResponse
import com.julietolieng.bills_app.ui.utilies.Constance
import com.julietolieng.bills_app.ui.viewModel.LoginViewModel

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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

            loginViewModel.loginUser(loginRequest)

        }
        fun initObservers(){
            loginViewModel.logLiveData.observe(this){
                loginResponse->persistLogin(loginResponse)
                Toast.makeText(this, loginResponse.message,Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Home::class.java))
            }
        }


        Toast.makeText(this, "login succesfully", Toast.LENGTH_SHORT).show()

    }
    fun persistLogin(loginResponse: LoginResponse){
        val sharedPrefs=getSharedPreferences(Constance.PREFS,Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()
        editor.putString(Constance.USER_ID,loginResponse.user_id)
        editor.putString(Constance.ACCESS_TOKEN,loginResponse.access_token)
        editor.apply()
    }


}