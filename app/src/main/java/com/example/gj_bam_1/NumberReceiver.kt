package com.example.gj_bam_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.Room
import com.example.gj_bam_1.database.UserData
import com.example.gj_bam_1.database.UserDataDatabase

class NumberReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val username = intent.getStringExtra("username")
        val number = intent.getIntExtra("number", 0)

        //Zadanie 4 - zapisanie danych przekazanych przez użytkownika w bazie Room
        val db = Room.databaseBuilder(
            context.applicationContext,
            UserDataDatabase::class.java, "userData-database"
        ).allowMainThreadQueries().build()
        db.userDataDao().insertAll(UserData(0, username, number))

        // Zadanie 3 - wylogowanie danych przekazanych do receivera
        Log.d(
            "NumberReceiver",
            "Nazwa użytkownika: $username. Licznik ostatnio uruchomionego serwisu: $number"
        )
    }
}