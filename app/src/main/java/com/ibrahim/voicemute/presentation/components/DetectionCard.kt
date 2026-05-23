package com.ibrahim.voicemute.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ibrahim.voicemute.ui.theme.VoiceMuteTheme

@Composable
fun DetectionCard(
    text: String
) {

    Card {

        Column(
            modifier = Modifier
                .background(VoiceMuteTheme.color.background)
                .padding(20.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = VoiceMuteTheme.color.primary)) {
                        append("Keyword")
                    }
                    withStyle(SpanStyle(color = VoiceMuteTheme.color.secondary)) {
                        append("Detected")
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = text)
        }
    }
}
