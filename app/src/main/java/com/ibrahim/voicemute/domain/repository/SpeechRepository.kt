package com.ibrahim.voicemute.domain.repository

import com.ibrahim.voicemute.core.speech.RecognitionState
import kotlinx.coroutines.flow.StateFlow

interface SpeechRepository {

    val recognitionState: StateFlow<RecognitionState>

    suspend fun startListening()

    fun stopListening()
}
