package com.ibrahim.voicemute.core.audio

import android.app.NotificationManager
import android.content.Context
import android.media.AudioManager
import android.util.Log

class AudioController(
    private val context: Context,
    private val audioManager: AudioManager
) {

    fun enableSilentMode() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (!notificationManager.isNotificationPolicyAccessGranted) {
            Log.e("AudioController", "DND permission NOT granted")
            return
        }
        audioManager.ringerMode =
            AudioManager.RINGER_MODE_SILENT
    }

    fun muteMediaVolume() {
        audioManager.adjustStreamVolume(
            AudioManager.STREAM_MUSIC,
            AudioManager.ADJUST_MUTE,
            0
        )
    }

    fun enableDnd() {
        val manager =
            context.getSystemService(NotificationManager::class.java)

        if (manager.isNotificationPolicyAccessGranted) {
            manager.setInterruptionFilter(
                NotificationManager.INTERRUPTION_FILTER_NONE
            )
        }
    }
}
