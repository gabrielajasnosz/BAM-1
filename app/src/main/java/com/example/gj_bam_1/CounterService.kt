package com.example.gj_bam_1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CounterService : Service() {
    private var isRunning = true
    private var lastServiceIndex = 0
    private val counters= mutableMapOf<Int, Int>()
    private var username: String? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        lastServiceIndex = intent.getIntExtra("serviceIndex", 1)
        username = intent.getStringExtra("username")
        startCountdown(lastServiceIndex)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startCountdown(indexx: Int) {
        // zadanie 2 - utworzenie wątku oraz logowanie licznika co sekundę
        counters[indexx] = 0;
        Thread {
            while (isRunning) {
                if(counters.isNotEmpty()){
                    try {
                        Log.d("Service $indexx", "Counter ${counters[indexx]}")
                        Thread.sleep(1000)
                        counters[indexx] = counters[indexx]!!.plus(1)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }

            }
        }.start()
    }

    override fun onDestroy() {
        isRunning = false
        val receiver = Intent("RECEIVE_NUMBER_AND_USERNAME")
        receiver.putExtra("username", username)
        receiver.putExtra("number", counters[lastServiceIndex])
        sendBroadcast(receiver)
        super.onDestroy()
    }
}