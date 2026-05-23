package com.ibrahim.voicemute.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ibrahim.voicemute.R
import com.ibrahim.voicemute.ui.theme.VoiceMuteTheme

@Composable
fun AnimatedMic(
    isListening: Boolean
) {

    val infinite = rememberInfiniteTransition(label = "mic_scale")

    val scale by infinite.animateFloat(
        initialValue = 1f,
        targetValue = if (isListening) 1.2f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mic_scale"
    )

    Icon(
        painterResource(R.drawable.icon_microphone),
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .scale(scale),
        tint = VoiceMuteTheme.color.primary.copy(alpha = 0.8f)
    )
}
