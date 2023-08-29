package com.julietolieng.bills_app.ui.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.julietolieng.bills_app.ui.model.Bill

interface BillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(bill: Bill){

    }
}