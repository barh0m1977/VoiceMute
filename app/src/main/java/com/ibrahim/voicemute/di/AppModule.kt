package com.ibrahim.voicemute.di

import android.content.Context
import android.media.AudioManager
import com.ibrahim.voicemute.core.audio.AudioController
import com.ibrahim.voicemute.core.speech.SpeechRecognitionManager
import com.ibrahim.voicemute.core.datastore.SettingsDataStore
import org.koin.dsl.module

val appModule = module {

    single {
        get<Context>().getSystemService(Context.AUDIO_SERVICE)
                as AudioManager
    }

    single {
        AudioController(get(), get())
    }

    single {
        SpeechRecognitionManager(get())
    }

    single {
        SettingsDataStore(get())
    }
}
