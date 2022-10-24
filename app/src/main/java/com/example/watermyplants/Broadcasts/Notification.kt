package com.example.watermyplants.Broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.watermyplants.R
import com.example.watermyplants.Utils.Constants.channelID


class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val notification = NotificationCompat.Builder(context!!, channelID).setSmallIcon(R.drawable.ic_add_icon).setContentTitle(
            "Titulo").setContentText("Descricao")

        val manager = NotificationManagerCompat.from(context)
        manager.notify(123, notification.build())
    }
}