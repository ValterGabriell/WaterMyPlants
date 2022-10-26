package com.example.watermyplants

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watermyplants.Adapter.MyAdapter
import com.example.watermyplants.Model.PlantItem
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.Utils.Constants.channelID
import com.example.watermyplants.Utils.Constants.channelName
import com.example.watermyplants.ViewModel.MainViewModel
import com.example.watermyplants.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private val viewModel: MainViewModel by viewModels()
    private val itemList = MutableLiveData<List<PlantItem>>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createExemplePlant()





        createNotificationChannel()
        CoroutineScope(Dispatchers.IO).launch {
            itemList.postValue(viewModel.getAllPlants())
        }


        itemList.observe(this) {
            adapter = MyAdapter(it)
            binding.recyclerWaterToday.adapter = adapter
            binding.recyclerWaterToday.layoutManager = LinearLayoutManager(this)
            adapter.onItemClick = { plant_id, _ ->
                val intent = Intent(this, DetailsScreen::class.java)
                intent.putExtra(Constants.PLANT_ID, plant_id)
                startActivity(intent)
            }
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

    private fun createExemplePlant() {
        val plantItem = PlantItem(
            1, "Planta exemplo", 45, Color.LTGRAY, Constants.HIGH, 16f, Constants.DAQUI6DIAS,
            "uri", false
        )


    }
}


