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


        binding.textView.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

    }


    private fun testData(): ArrayList<PlantItem> {
        return arrayListOf(
            PlantItem(
                "Primeira Planta",
                250,
                Color.YELLOW,
                StringsConstantes.LOW,
                24.4,
                StringsConstantes.DAILY,
                null
            ),
            PlantItem(
                "Segunda Planta",
                250,
                Color.CYAN,
                StringsConstantes.LOW,
                24.4,
                StringsConstantes.DAILY,
                null
            ),
            PlantItem(
                "Terceira Planta",
                250,
                Color.LTGRAY,
                StringsConstantes.LOW,
                24.4,
                StringsConstantes.DAILY,
                null
            )
        )
    }
}


