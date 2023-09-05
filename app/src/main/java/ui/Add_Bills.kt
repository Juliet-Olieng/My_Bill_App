package ui

import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.julietolieng.bills_app.R
import com.julietolieng.bills_app.databinding.ActivityAddBillsBinding
import com.julietolieng.bills_app.ui.model.Bill
import com.julietolieng.bills_app.ui.utilies.Constance
import java.util.UUID

class Add_Bills : AppCompatActivity() {
    lateinit var binding: ActivityAddBillsBinding
    var selectedMonth=0
    var selectedDate=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBillsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        setupFreqSpinner()
//        validateBill()
    }
     fun setupFreqSpinner(){
         val frequencies = arrayOf("weekly","Monthly","Annual" )
         val freqAdapter=ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,frequencies)
         freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
         binding.spFreq.adapter=freqAdapter
         binding.spFreq.onItemSelectedListener =object:OnItemSelectedListener{
             override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                 validateBill()
                when(binding.spFreq.selectedItem.toString()){
                    Constance.WEEKLY->{
                        setUpDueDateSpinner(Array(7){it +1})
                        hideDatePicker()


                    }
                    Constance.MONTHLY->{
                        setUpDueDateSpinner(Array(31){it +1})
                        hideDatePicker()

                    }
                    Constance.ANNUAL->{
                        showDatePicker()
                        hideDatePicker()

                    }
                }

             }


             override fun onNothingSelected(p0: AdapterView<*>?) {
                 TODO("Not yet implemented")
             }
         }

     }

    fun setUpDueDateSpinner(dates:Array<Int>){
        val dueDateAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,dates)
        dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDate.adapter=dueDateAdapter
    }

    fun showDatePicker(){
        binding.spDate.hide()
        binding.dpDueDate.show()
    }

    fun hideDatePicker(){
        binding.dpDueDate.hide()
        binding.spDate.show()
    }

    fun setUpDueDate(){
        val calender=Calendar.getInstance()
        binding.dpDueDate.init(
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        ){
            view,year,month,date ->
            selectedDate=date
            selectedMonth=month+1
        }
    }

    fun validateBill(){
        val billName=binding.etBillName.text.toString()
        val billAmount=binding.etAmount.text.toString()
        val freguency=binding.tvFreqSpinner.text.toString()
        var dueDate=Constance.EMPTY_STRINGS
        if (freguency==Constance.ANNUAL){
            dueDate="$selectedDate/$selectedMonth"
        }
        else{
            dueDate=binding.spDate.selectedItem.toString()
        }
        var error=true
        if (billName.isBlank()){
            error=true
            binding.etBillName.error="Bill name required"
        }
        if (!error){
            val prefs=getSharedPreferences(Constance.PREFS,Context.MODE_PRIVATE)
            val userId=prefs.getString(Constance.USER_ID,Constance.EMPTY_STRINGS)
            val bill=Bill(
                billId = UUID.randomUUID().toString(),
                name = billName,
                amount = billAmount.toDouble(),
                frequency = freguency,
                dueDate = dueDate,
                userId = userId.toString()
            )
        }
    }

    private fun View.show(){
        this.visibility=View.VISIBLE
    }
    fun View.hide(){
        this.visibility=View.GONE
    }
    fun resetForm(){
        binding.etBillName.setText(Constance.EMPTY_STRINGS)
        binding.etAmount.setText(Constance.EMPTY_STRINGS)
        binding.spFreq.setSelection(0)
        hideDatePicker()
        binding.spDate.setSelection(0)
    }
}