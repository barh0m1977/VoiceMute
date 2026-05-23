package com.ibrahim.voicemute.domain.usecase

import com.ibrahim.voicemute.domain.repository.SpeechRepository

class StopListeningUseCase(
    private val repository: SpeechRepository
) {

    operator fun invoke() {
        repository.stopListening()
    }
}
