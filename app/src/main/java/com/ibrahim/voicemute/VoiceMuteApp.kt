package com.ibrahim.voicemute

import android.app.Application
import com.ibrahim.voicemute.di.appModule
import com.ibrahim.voicemute.di.repositoryModule
import com.ibrahim.voicemute.di.useCaseModule
import com.ibrahim.voicemute.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VoiceMuteApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VoiceMuteApp)

            modules(
                appModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
