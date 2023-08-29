package ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.julietolieng.bills_app.R
import com.julietolieng.bills_app.databinding.ActivityMainBinding
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
            startActivity(Intent(this@Home, MainActivity::class.java))
            finish()

        }
        binding.btnAdd.setOnClickListener {
            val intent= Intent(this,Add_Bills::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNav()

    }
    fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { menuItem->
          when(menuItem.itemId){
              R.id.Summary ->{
                  supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.fcvHome, SummaryFragment())
                      .commit()
                  true
              }
              R.id.Upcoming ->{
                  supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.fcvHome, UpcomingBillsFragment())
                      .commit()
                  true
              }
              R.id.Paid ->{
                  supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.fcvHome, PaidBillsFragments())
                      .commit()
                  true
              }
              R.id.Settings->{
                  supportFragmentManager
                      .beginTransaction()
                      .replace(R.id.fcvHome, SettingsFragment())
                      .commit()
                  true
              }
              else ->{
                  false
              }

          }
        }
    }
}