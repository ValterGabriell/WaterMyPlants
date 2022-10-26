package com.example.watermyplants

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.watermyplants.Broadcasts.ReminderBroadcast
import com.example.watermyplants.Chips.toChip
import com.example.watermyplants.Model.PlantItem
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.Utils.Constants.filterChipToSave
import com.example.watermyplants.Utils.Constants.makeAToast
import com.example.watermyplants.ViewModel.CameraViewModel
import com.example.watermyplants.databinding.ActivityCameraBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.ByteArrayOutputStream
import java.util.*


class CameraActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {


    private lateinit var binding: ActivityCameraBinding
    private val viewModel: CameraViewModel by viewModels()
    private lateinit var imgBA: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Adicionar planta"
        configureChip()

        val hasPermissions = methodRequireCameraPermission()
        if (hasPermissions) {
            setCameraLaunch()
        }
    }


    private fun methodRequireCameraPermission(): Boolean {
        val perms = Manifest.permission.CAMERA
        return if (EasyPermissions.hasPermissions(this, perms)) {
            true
        } else {
            EasyPermissions.requestPermissions(this, "rationale", 0, perms)
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    private fun configureChip() {
        Constants.filters_color.forEach { filterItem ->
            binding.chipGroupFilter.apply {
                addView(filterItem.toChip(this@CameraActivity))
            }
        }

        Constants.filters_light.forEach { filterItemLight ->
            binding.chipGroupFilterLight.addView(filterItemLight.toChip(this))
        }

        Constants.filter_frequency.forEach { filter_frequency ->
            binding.chipGroupFilterFrequency.addView(filter_frequency.toChip(this))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_camera, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_camera_btn_save -> {
                savePlant()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savePlant() {

        val txt_title = binding.activityCameraTitleEditText.text.toString()
        val txt_qtd = binding.activityCameraMlEditText.text.toString()
        val txt_temperature = binding.activityCameraTemperatureEditText.text.toString()
        val color_chip = Constants.filterChipToSave(binding.chipGroupFilter)

        val day_chip = Constants.filterChipToSave(binding.chipGroupFilterFrequency)

        var color = 0
        when (color_chip) {
            "1" -> {
                color = Color.YELLOW
            }
            "2" -> {
                color = Color.CYAN
            }
            "3" -> {
                color = Color.LTGRAY
            }
        }

        if (txt_title.isNotEmpty() && txt_qtd.isNotEmpty() && txt_temperature.isNotEmpty()) {
            val plantItem = PlantItem(
                System.currentTimeMillis().toInt(),
                txt_title,
                txt_qtd.toInt(),
                color,
                Constants.filterChipToSave(binding.chipGroupFilterLight),
                txt_temperature.toFloat(),
                day_chip,
                imgBA,
                false
            )
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.inserNewPlant(plantItem)
                CoroutineScope(Dispatchers.Main).launch {
                    Constants.makeAToast(
                        this@CameraActivity,
                        "Planta adicionada com sucesso, frequência: $day_chip",
                        1
                    )
                    startActivity(Intent(this@CameraActivity, MainActivity::class.java))
                }


            }.invokeOnCompletion {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.i(Constants.TAG, plantItem.id.toString())
                    scheduleNotification(day_chip, plantItem.id, plantItem)
                }

            }

        }


    }


    private fun scheduleNotification(day: String, id: Int, plantItem: PlantItem) {
        Log.i(Constants.TAG, plantItem.toString())
        val intent = Intent(applicationContext, ReminderBroadcast::class.java)
        intent.putExtra("description", day)
        intent.putExtra("id", id)
        intent.putExtra("title", plantItem.title)
        val pendingIntent =
            PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        //setando para o alarme disparar as 12h30
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
        }

        when (day) {
            Constants.DAQUI1DIA -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
            Constants.DAQUI2DIAS -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 2,
                    pendingIntent
                )
            }
            Constants.DAQUI3DIAS -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 3,
                    pendingIntent
                )
            }
            Constants.DAQUI4DIAS -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 4,
                    pendingIntent
                )
            }
            Constants.DAQUI5DIAS -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 5,
                    pendingIntent
                )
            }
            Constants.DAQUI6DIAS -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 6,
                    pendingIntent
                )
            }
            Constants.DAQUI7DIAS -> {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }
        }
    }


    private fun setCameraLaunch() {
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                val inputStr = contentResolver.openInputStream(uri!!)
                val image = BitmapFactory.decodeStream(inputStr)
                binding.activityCameraImg.setImageBitmap(image)
                imgBA = image

            }
        return getContent.launch("image/*")
    }

}