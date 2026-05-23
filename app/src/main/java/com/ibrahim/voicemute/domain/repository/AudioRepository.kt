package com.ibrahim.voicemute.domain.repository

interface AudioRepository {

    fun silencePhone()

    fun muteMedia()

    fun enableDnd()
}
