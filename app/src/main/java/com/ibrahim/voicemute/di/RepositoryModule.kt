package com.ibrahim.voicemute.di

import com.ibrahim.voicemute.data.repository.AudioRepositoryImpl
import com.ibrahim.voicemute.data.repository.SettingsRepositoryImpl
import com.ibrahim.voicemute.data.repository.SpeechRepositoryImpl
import com.ibrahim.voicemute.domain.repository.AudioRepository
import com.ibrahim.voicemute.domain.repository.SettingsRepository
import com.ibrahim.voicemute.domain.repository.SpeechRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<AudioRepository> {
        AudioRepositoryImpl(get())
    }

    single<SpeechRepository> {
        SpeechRepositoryImpl(get())
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(get())
    }
}
