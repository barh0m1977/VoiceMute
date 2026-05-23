package com.ibrahim.voicemute.domain.usecase

import com.ibrahim.voicemute.domain.repository.AudioRepository

class EnableSilentModeUseCase(
    private val repository: AudioRepository
) {

    operator fun invoke() {
        repository.silencePhone()
        repository.muteMedia()
        repository.enableDnd()
    }
}
