package com.ibrahim.voicemute.domain.usecase

import com.ibrahim.voicemute.domain.repository.SpeechRepository

class StartListeningUseCase(
    private val repository: SpeechRepository
) {

    suspend operator fun invoke() {
        repository.startListening()
    }
}
