package com.example.watermyplants

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watermyplants.Adapter.MyAdapter
import com.example.watermyplants.Broadcasts.ReminderBroadcast
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.Utils.Constants.channelID
import com.example.watermyplants.Utils.Constants.channelName
import com.example.watermyplants.Utils.Constants.testData
import com.example.watermyplants.databinding.ActivityMainBinding
import kotlinx.datetime.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()



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

    }





    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val description = "Canal para alarmar noticações"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelID, channelName, importance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }


}


