package com.julietolieng.bills_app.ui.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
//accessing database using a singleton class
abstract class BillsDb:RoomDatabase() {
    abstract fun billsDao():BillsDao
    companion object{
        var database:BillsDb?=null

        fun getDatabase(context: Context):BillsDb{
            if (database==null){
                database=Room.databaseBuilder(context,BillsDb::class.java, "BillsDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillsDb
        }
    }
}