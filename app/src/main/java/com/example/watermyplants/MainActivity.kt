package com.example.watermyplants

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watermyplants.Adapter.MyAdapter
import com.example.watermyplants.Model.PlantItem
import com.example.watermyplants.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = MyAdapter(testData())
        binding.recyclerWaterToday.adapter = adapter
        binding.recyclerWaterToday.layoutManager = LinearLayoutManager(this)
        adapter.onItemClick = { plant_id, position ->
            startActivity(Intent(this, DetailsScreen::class.java))
        }


        binding.textView.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

    }


    private fun testData(): ArrayList<PlantItem> {
        return arrayListOf(
            PlantItem(
                1,
                "Primeira Planta",
                250,
                Color.YELLOW,
                Constants.LOW,
                24.4,
                Constants.DAILY,
                null
            ),
            PlantItem(
                2,
                "Segunda Planta",
                250,
                Color.CYAN,
                Constants.LOW,
                24.4,
                Constants.DAILY,
                null
            ),
            PlantItem(
                3,
                "Terceira Planta",
                250,
                Color.LTGRAY,
                Constants.LOW,
                24.4,
                Constants.DAILY,
                null
            )
        )
    }
}


