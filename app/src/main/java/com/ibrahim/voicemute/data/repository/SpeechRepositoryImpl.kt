package com.ibrahim.voicemute.data.repository

import com.ibrahim.voicemute.core.speech.RecognitionState
import com.ibrahim.voicemute.core.speech.SpeechRecognitionManager
import com.ibrahim.voicemute.domain.repository.SpeechRepository
import kotlinx.coroutines.flow.StateFlow

class SpeechRepositoryImpl(
    private val manager: SpeechRecognitionManager
) : SpeechRepository {

    override val recognitionState: StateFlow<RecognitionState> = manager.state

    override suspend fun startListening() {
        manager.startListening()
    }

    override fun stopListening() {
        manager.stopListening()
    }
}
