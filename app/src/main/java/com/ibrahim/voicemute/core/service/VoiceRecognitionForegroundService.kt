package com.ibrahim.voicemute.core.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ibrahim.voicemute.core.speech.RecognitionState
import com.ibrahim.voicemute.domain.repository.AudioRepository
import com.ibrahim.voicemute.domain.repository.SpeechRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class VoiceRecognitionForegroundService : Service() {

    private val speechRepository: SpeechRepository by inject()
    private val audioRepository: AudioRepository by inject()

    private val serviceScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )

    override fun onCreate() {
        super.onCreate()

        startForeground(
            1,
            NotificationHelper.createNotification(this)
        )
        Log.d("TestApp", "Foreground started")

        observeRecognition()

        serviceScope.launch {
            speechRepository.startListening()
        }
    }

    private fun observeRecognition() {
        serviceScope.launch {
            speechRepository.recognitionState.collect { state ->
                if (state is RecognitionState.Detected) {
                    try {
                        audioRepository.silencePhone()
                        audioRepository.muteMedia()
                        audioRepository.enableDnd()
                    } catch (e: SecurityException) {
                        Log.e("VoiceService", "Missing system permission", e)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        speechRepository.stopListening()
        serviceScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
