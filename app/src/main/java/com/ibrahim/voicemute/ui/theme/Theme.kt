package com.ibrahim.voicemute.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.ibrahim.voicemute.ui.semanticToken.ColorScheme

object VoiceMuteTheme{
    val color : ColorScheme
        @Composable @ReadOnlyComposable get() = LocalColor.current
}

