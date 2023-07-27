package com.julietolieng.bills_app.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.julietolieng.bills_app.databinding.HomePageBinding

class Home:AppCompatActivity (){
    lateinit var binding: HomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}