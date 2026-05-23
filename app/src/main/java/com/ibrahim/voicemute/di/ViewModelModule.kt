package com.ibrahim.voicemute.di

import com.ibrahim.voicemute.presentation.home.HomeViewModel
import com.ibrahim.voicemute.presentation.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(
            startListeningUseCase = get(),
            stopListeningUseCase = get(),
            observeRecognitionUseCase = get()
        )
    }

    viewModel {
        SettingsViewModel(get())
    }
}
