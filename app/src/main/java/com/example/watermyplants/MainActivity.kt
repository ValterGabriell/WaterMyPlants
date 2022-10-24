package com.example.watermyplants

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watermyplants.Adapter.MyAdapter
import com.example.watermyplants.Broadcasts.Notification
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.Utils.Constants.notificationID
import com.example.watermyplants.Utils.Constants.testData
import com.example.watermyplants.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import java.util.*
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        scheduleNotification()


        adapter = MyAdapter(Constants.testData())
        binding.recyclerWaterToday.adapter = adapter
        binding.recyclerWaterToday.layoutManager = LinearLayoutManager(this)
        adapter.onItemClick = { plant_id, _ ->
            val intent = Intent(this, DetailsScreen::class.java)
            intent.putExtra(Constants.PLANT_ID, plant_id)
            startActivity(intent)
        }

        binding.include.btnToolbarAddPlant.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        binding.textView.setOnClickListener {
            scheduleNotification()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, Clock.System.now().plus(3, DateTimeUnit.SECOND).toEpochMilliseconds(), AlarmManager.INTERVAL_DAY, pendingIntent)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif channel"
        val desc = "description"
        val importante = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("one", name, importante)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }





}


