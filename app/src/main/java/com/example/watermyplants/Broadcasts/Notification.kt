package com.example.watermyplants.Broadcasts

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.watermyplants.MainActivity
import com.example.watermyplants.R
import com.example.watermyplants.Utils.Constants.channelID


class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, MainActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        val builder = NotificationCompat.Builder(context!!, "watermyplant")
            .setSmallIcon(R.drawable.ic_add_icon)
            .setContentTitle("Title")
            .setContentText("Subscribe")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)


        val notifcationManager = NotificationManagerCompat.from(context)
        notifcationManager.notify(123, builder.build())
    }


}