package com.ibrahim.voicemute

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ibrahim.voicemute.presentation.components.DetectionCard
import com.ibrahim.voicemute.presentation.home.HomeScreen
import com.ibrahim.voicemute.presentation.navigation.AppNavigation
import com.ibrahim.voicemute.ui.semanticToken.defaultLightColor
import com.ibrahim.voicemute.ui.theme.IndexerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isDndGranted()) {
            requestDndPermission(this)
        }
        setContent {
            IndexerTheme(defaultLightColor) {
                HomeScreen()
            }
        }
    }

    private fun isDndGranted(): Boolean {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return nm.isNotificationPolicyAccessGranted
    }

    fun requestDndPermission(context: Context) {
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
