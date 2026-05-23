package com.ibrahim.voicemute.domain.usecase

import com.ibrahim.voicemute.domain.repository.SpeechRepository

class ObserveRecognitionUseCase(
    private val repository: SpeechRepository
) {

    operator fun invoke() = repository.recognitionState
}
