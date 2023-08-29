package ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.julietolieng.bills_app.R
import com.julietolieng.bills_app.databinding.ActivityAddBillsBinding

class Add_Bills : AppCompatActivity() {
    lateinit var binding: ActivityAddBillsBinding
    var frequency= arrayOf("monthly","daily","weekly")
    var dueDate= arrayOf("20/8/2023","4/9/2023","20/9/2023")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBillsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spinner2=findViewById<Spinner>(R.id.pDueDate)
        val spinner= findViewById<Spinner>(R.id.pFrequency)
        val arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,frequency)
        val arrayAdapter2=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,dueDate)
        spinner.adapter=arrayAdapter
        spinner2.adapter=arrayAdapter2
        spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long) {
               Toast.makeText(applicationContext,"selected frequency is="+frequency[0],Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinner2.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long) {
                Toast.makeText(applicationContext,"selected duedate is"+dueDate[0],Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
}