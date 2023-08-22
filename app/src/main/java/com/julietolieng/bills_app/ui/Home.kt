package com.julietolieng.bills_app.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.julietolieng.bills_app.databinding.HomePageBinding
import com.julietolieng.bills_app.ui.utilies.Constance

class Home:AppCompatActivity (){
    private lateinit var binding: HomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val sharedPreferences=getSharedPreferences(Constance.PREFS,Context.MODE_PRIVATE)
        val userId=sharedPreferences.getString(Constance.USER_ID,Constance.EMPTY_STRINGS)?:Constance.EMPTY_STRINGS


        binding.btnLogOut.setOnClickListener {
            val editor=sharedPreferences.edit()
            editor.apply()
            editor.clear()
            startActivity(Intent(this@Home,MainActivity::class.java))
            finish()

        }
    }
}