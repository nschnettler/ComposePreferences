package de.schnettler.datastore.compose

import androidx.compose.runtime.*
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi

val LocalPreferenceEnabledStatus: ProvidableCompositionLocal<Boolean> =
    compositionLocalOf(structuralEqualityPolicy()) { true }