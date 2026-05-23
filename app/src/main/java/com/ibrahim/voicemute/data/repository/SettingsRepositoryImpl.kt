package com.ibrahim.voicemute.data.repository

import com.ibrahim.voicemute.core.datastore.SettingsDataStore
import com.ibrahim.voicemute.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val dataStore: SettingsDataStore
) : SettingsRepository
