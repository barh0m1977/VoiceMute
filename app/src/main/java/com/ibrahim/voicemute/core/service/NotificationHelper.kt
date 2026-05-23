package com.ibrahim.voicemute.core.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

object NotificationHelper {

    private const val CHANNEL_ID = "voice_mute_channel"

    fun createNotification(
        context: Context
    ): Notification {

        val manager =
            context.getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(
            CHANNEL_ID,
            "VoiceMute Service",
            NotificationManager.IMPORTANCE_LOW
        )

        manager.createNotificationChannel(channel)

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("VoiceMute")
            .setContentText("Listening for keyword")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .build()
    }
}
