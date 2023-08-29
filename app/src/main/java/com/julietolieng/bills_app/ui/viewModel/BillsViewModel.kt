package com.julietolieng.bills_app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julietolieng.bills_app.ui.model.Bill
import com.julietolieng.bills_app.ui.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel:ViewModel (){
    val billsrepo=BillsRepository()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsrepo.saveBill(bill)
        }
    }
}