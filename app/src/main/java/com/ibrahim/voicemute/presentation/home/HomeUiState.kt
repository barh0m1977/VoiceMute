package com.ibrahim.voicemute.presentation.home

data class HomeUiState(
    val isListening: Boolean = false,
    val lastPhrase: String = "",
    val showDetection: Boolean = false,
    val error: String? = null
)
