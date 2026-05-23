package com.ibrahim.voicemute.presentation.home

import android.Manifest
import android.content.Intent
import android.content.res.Resources
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ibrahim.voicemute.core.service.VoiceRecognitionForegroundService
import com.ibrahim.voicemute.presentation.components.AnimatedMic
import com.ibrahim.voicemute.presentation.components.DetectionCard
import com.ibrahim.voicemute.ui.theme.IndexerTheme
import com.ibrahim.voicemute.ui.theme.VoiceMuteTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AnimatedMic(state.isListening)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = if (state.isListening)
                    "Listening..."
                else
                    "Stopped",
                style = if (state.isListening){
                    TextStyle(
                        color = VoiceMuteTheme.color.primary
                    )
                }else{
                    TextStyle(
                        color = VoiceMuteTheme.color.error
                    )
                }

            )

            Spacer(modifier = Modifier.height(20.dp))
            val permissionLauncher =
                rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { granted ->
                    if (granted) {

                        ContextCompat.startForegroundService(
                            context,
                            Intent(
                                context,
                                VoiceRecognitionForegroundService::class.java
                            )
                        )
                    }
                }

            Button(
                onClick = {

                    permissionLauncher.launch(
                        Manifest.permission.RECORD_AUDIO
                    )
                },
                colors = ButtonColors(
                    VoiceMuteTheme.color.primary,
                    VoiceMuteTheme.color.onPrimary,
                    VoiceMuteTheme.color.secondaryVariant,
                    VoiceMuteTheme.color.secondary
                )
            ) {
                Text("Start Listening")
            }


            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {

                    context.stopService(
                        Intent(
                            context,
                            VoiceRecognitionForegroundService::class.java
                        )
                    )

                    viewModel.stopListening()
                },
                colors = ButtonColors(
                    VoiceMuteTheme.color.container,
                    VoiceMuteTheme.color.primary,
                    VoiceMuteTheme.color.disable,
                    VoiceMuteTheme.color.onDisable
                )
            ) {
                Text("Stop Listening")
            }

            AnimatedVisibility(state.showDetection) {
                Spacer(modifier = Modifier.height(16.dp))
                DetectionCard(state.lastPhrase)
                context.stopService(
                    Intent(
                        context,
                        VoiceRecognitionForegroundService::class.java
                    )
                )
                viewModel.stopListening()
            }
        }
    }
}
