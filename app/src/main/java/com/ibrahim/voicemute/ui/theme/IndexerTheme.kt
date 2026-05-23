package com.ibrahim.voicemute.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.ibrahim.voicemute.ui.semanticToken.ColorScheme
import com.ibrahim.voicemute.ui.semanticToken.defaultLightColor

@Composable
fun IndexerTheme(
    colorScheme: ColorScheme = defaultLightColor,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColor provides colorScheme
    ) {
        content()
    }
}