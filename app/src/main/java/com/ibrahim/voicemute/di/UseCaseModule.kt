package com.ibrahim.voicemute.di

import com.ibrahim.voicemute.domain.usecase.StartListeningUseCase
import com.ibrahim.voicemute.domain.usecase.StopListeningUseCase
import com.ibrahim.voicemute.domain.usecase.EnableSilentModeUseCase
import com.ibrahim.voicemute.domain.usecase.ObserveRecognitionUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        StartListeningUseCase(get())
    }

    factory {
        StopListeningUseCase(get())
    }

    factory {
        EnableSilentModeUseCase(get())
    }

    factory {
        ObserveRecognitionUseCase(get())
    }
}
