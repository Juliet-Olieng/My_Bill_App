package com.julietolieng.bills_app.ui.repository

import com.julietolieng.bills_app.ui.BillsApp
import com.julietolieng.bills_app.ui.database.BillsDb
import com.julietolieng.bills_app.ui.model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    var db=BillsDb.getDatabase(BillsApp.appContext)
    val billsDao=db.billsDao()

    suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            billsDao.insertBill(bill)
        }
    }
}