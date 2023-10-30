package com.example.gj_bam_1

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.room.Room
import com.example.gj_bam_1.database.UserDataDatabase
import java.util.concurrent.atomic.AtomicInteger


class UserActivity : ComponentActivity() {
    private val numberOfServices = AtomicInteger(0)
    private val numberReceiver = NumberReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        registerReceiver(numberReceiver, IntentFilter("RECEIVE_NUMBER_AND_USERNAME"))

        val inputValue = findViewById<TextView>(R.id.userName)
        val value: String = intent.getStringExtra("name").toString()
        inputValue.text = value

        val runServicesButton = findViewById<Button>(R.id.runServices)
        val counterService = Intent(this, CounterService::class.java)
        runServicesButton.setOnClickListener {
            counterService.putExtra("username", value)
            counterService.putExtra("serviceIndex", numberOfServices.incrementAndGet())
            startService(counterService)
        }

        val stopServicesButton = findViewById<Button>(R.id.stopServices)
        stopServicesButton.setOnClickListener {
            stopService(counterService)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            UserDataDatabase::class.java, "userData-database"
        ).allowMainThreadQueries().build()

        val readFromDatabase = findViewById<Button>(R.id.readDataBase)
        readFromDatabase.setOnClickListener {
            db.userDataDao().getAll().forEach {data ->
                Log.d("Data from database:",  "Username: ${data.username}, number: ${data.number}")
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(numberReceiver)
    }
}