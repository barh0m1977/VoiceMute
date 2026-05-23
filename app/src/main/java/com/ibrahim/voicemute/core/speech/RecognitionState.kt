package com.ibrahim.voicemute.core.speech

sealed class RecognitionState {

    data object Idle : RecognitionState()

    data object Listening : RecognitionState()

    data class Detected(
        val text: String
    ) : RecognitionState()

    data class Error(
        val message: String
    ) : RecognitionState()
}
