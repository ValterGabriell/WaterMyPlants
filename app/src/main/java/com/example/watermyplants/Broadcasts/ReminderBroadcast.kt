package com.example.watermyplants.Broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.watermyplants.R
import com.example.watermyplants.Utils.Constants


class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val description = p1?.extras?.get("description")
        val id = p1?.extras?.getInt("id")
        val title = p1?.extras?.get("title")
        val builder = NotificationCompat.Builder(p0!!, Constants.channelID)
            .setSmallIcon(R.drawable.ic_baseline_invert_colors_24)
            .setContentTitle(title.toString()).setContentText("Frequência de regagem: $description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        val notificationManager = NotificationManagerCompat.from(p0)

        notificationManager.notify(id!!, builder.build())
    }
}