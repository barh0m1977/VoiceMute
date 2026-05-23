package com.ibrahim.voicemute.data.repository

import com.ibrahim.voicemute.core.audio.AudioController
import com.ibrahim.voicemute.domain.repository.AudioRepository

class AudioRepositoryImpl(
    private val controller: AudioController
) : AudioRepository {

    override fun silencePhone() {
        controller.enableSilentMode()
    }

    override fun muteMedia() {
        controller.muteMediaVolume()
    }

    override fun enableDnd() {
        controller.enableDnd()
    }
}
