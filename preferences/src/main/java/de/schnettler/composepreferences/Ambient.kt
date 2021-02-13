package de.schnettler.composepreferences

import androidx.compose.runtime.*
import com.tfcporciuncula.flow.FlowSharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val LocalPreferences: ProvidableCompositionLocal<FlowSharedPreferences> =
    compositionLocalOf(structuralEqualityPolicy()) { error("No preferences found") }

@ExperimentalCoroutinesApi
@Composable
fun ProvidePreferences(
    sharedPreferences: FlowSharedPreferences,
    content: @Composable () -> Unit
) {
    Providers(LocalPreferences provides sharedPreferences) {
        content()
    }
}

val LocalPreferenceEnabledStatus: ProvidableCompositionLocal<Boolean> =
    compositionLocalOf(structuralEqualityPolicy()) { true }