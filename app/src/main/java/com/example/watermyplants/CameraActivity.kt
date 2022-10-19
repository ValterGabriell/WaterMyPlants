package com.example.watermyplants

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.watermyplants.Chips.FilterItemColor
import com.example.watermyplants.Chips.FilterItemFrequency
import com.example.watermyplants.Chips.FilterItemLight
import com.example.watermyplants.Chips.toChip
import com.example.watermyplants.databinding.ActivityCameraBinding
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import kotlin.random.Random

class CameraActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {


    private lateinit var binding: ActivityCameraBinding

    private var filters_color = arrayOf(
        FilterItemColor(0),
        FilterItemColor(1),
        FilterItemColor(2)
    )

    private var filters_light = arrayOf(
        FilterItemLight(0),
        FilterItemLight(1)
    )

    private var filter_frequency = arrayOf(
        FilterItemFrequency(0),
        FilterItemFrequency(1),
        FilterItemFrequency(2),
        FilterItemFrequency(3),
        FilterItemFrequency(4),
        FilterItemFrequency(5),
        FilterItemFrequency(6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureChip()

        val hasPermissions = methodRequireCameraPermission()
        if (hasPermissions) {

            val getContent =
                registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                    doSomethingWithURI(uri!!)
                }
            getContent.launch("image/*")


        }




    }

    private fun doSomethingWithURI(uri: Uri) {
        binding.activityCameraImg.visibility = View.VISIBLE
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
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                doSomethingWithURI(uri!!)
            }
        getContent.launch("image/*")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    private fun configureChip() {
        filters_color.forEach { filterItem ->
            binding.chipGroupFilter.addView(filterItem.toChip(this))
        }

        filters_light.forEach { filterItemLight ->
            binding.chipGroupFilterLight.addView(filterItemLight.toChip(this))
        }

        filter_frequency.forEach { filter_frequency ->
            binding.chipGroupFilterFrequency.addView(filter_frequency.toChip(this))
        }



    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(4)
    }

}