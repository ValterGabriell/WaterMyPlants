package com.example.watermyplants

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.ViewModel.DetailsViewModel
import com.example.watermyplants.databinding.ActivityDetailsScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val plantId = intent.getIntExtra(Constants.PLANT_ID, 999999)
        CoroutineScope(Dispatchers.IO).launch {
            val plantItem = viewModel.getPlantPerId(plantId)
            Log.i(Constants.TAG, plantItem.id.toString())
            binding.textView2.text = plantItem.title
            binding.textView4.text = plantItem.frequencyDay.toString()
            binding.textView8.text = plantItem.light
            binding.textView9.text = plantItem.ml.toString() + "ml"
            binding.textView91.text = plantItem.temperature.toString() + "C"
            binding.ln.setBackgroundColor(plantItem.plantColor)
        }

        binding.floatingActionButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deletePlantPerId(plantId)
                val pendingIntent =
                    PendingIntent.getBroadcast(
                        this@DetailsScreen,
                        plantId,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                alarmManager.cancel(pendingIntent)

                val intent = Intent(this@DetailsScreen, MainActivity::class.java)
                startActivity(intent)
            }
        }


    }
}