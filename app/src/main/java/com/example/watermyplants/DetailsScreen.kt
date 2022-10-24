package com.example.watermyplants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.Utils.Constants.makeAToast
import com.example.watermyplants.databinding.ActivityDetailsScreenBinding

class DetailsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val plant_id = intent.getIntExtra(Constants.PLANT_ID, 999999)
        Constants.makeAToast(this, plant_id.toString(), 0)




    }
}