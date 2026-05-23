package com.ibrahim.voicemute.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.voicemute.core.speech.RecognitionState
import com.ibrahim.voicemute.domain.usecase.ObserveRecognitionUseCase
import com.ibrahim.voicemute.domain.usecase.StartListeningUseCase
import com.ibrahim.voicemute.domain.usecase.StopListeningUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val startListeningUseCase: StartListeningUseCase,
    private val stopListeningUseCase: StopListeningUseCase,
    private val observeRecognitionUseCase: ObserveRecognitionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        observeRecognition()
    }

    private fun observeRecognition() {
        viewModelScope.launch {
            observeRecognitionUseCase().collect { state ->
                when (state) {
                    is RecognitionState.Listening -> {
                        _state.update {
                            it.copy(isListening = true)
                        }
                    }

                    is RecognitionState.Detected -> {
                        _state.update {
                            it.copy(
                                showDetection = true,
                                lastPhrase = state.text
                            )
                        }
                    }

                    is RecognitionState.Error -> {
                        _state.update {
                            it.copy(error = state.message)
                        }
                    }

                    RecognitionState.Idle -> {
                        _state.update {
                            it.copy(isListening = false)
                        }
                    }
                }
            }
        }
    }

    fun startListening() {
        viewModelScope.launch {
            startListeningUseCase()
        }
    }

    fun stopListening() {
        stopListeningUseCase()
    }
}
