package com.example.watermyplants

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.watermyplants.Broadcasts.ReminderBroadcast
import com.example.watermyplants.Chips.toChip
import com.example.watermyplants.Utils.Constants
import com.example.watermyplants.Utils.Constants.filterChipToSave
import com.example.watermyplants.Utils.Constants.filterDay
import com.example.watermyplants.Utils.Constants.makeAToast
import com.example.watermyplants.databinding.ActivityCameraBinding
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class CameraActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {


    private lateinit var binding: ActivityCameraBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureChip()

        val hasPermissions = methodRequireCameraPermission()
        if (hasPermissions) {
            setCameraLaunch()
        }
    }

    private fun doSomethingWithURI(uri: Uri) {
        binding.activityCameraImg.setImageURI(uri)
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
        setCameraLaunch()
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

        val light_chip = Constants.filterChipToSave(binding.chipGroupFilterLight)
        val color_chip = Constants.filterChipToSave(binding.chipGroupFilter)

        val day_chip = Constants.filterChipToSave(binding.chipGroupFilterFrequency)
        val strWithNoSpaces = day_chip.replace(", ", ",")


        Constants.filterDay(day_chip)
        scheduleNotification(day_chip, txt_title)


    }


    private fun scheduleNotification(day:String, plantname:String) {
        val intent = Intent(this, ReminderBroadcast::class.java)
        intent.putExtra("name", "Planta1")
        intent.putExtra("description", day)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val long = System.currentTimeMillis()
        val fiveSecond = 1000 * 2
        alarmManager.set(AlarmManager.RTC_WAKEUP, long + fiveSecond, pendingIntent)

        Constants.makeAToast(this, "$plantname foi programada para alarmar $day", 1)
    }


    private fun checkDayInsideTheList(arrayInStr: String): ArrayList<String> {
        val arrayWithDays = arrayListOf<String>()
        val strWithNoSpaces = arrayInStr.replace(", ", ",")
        val strInArray = strWithNoSpaces.split(".")

        strInArray.forEach {
            arrayWithDays.add(it)
        }

        return arrayWithDays


    }

    private fun setCameraLaunch() {
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                doSomethingWithURI(uri!!)
            }
        return getContent.launch("image/*")
    }

}