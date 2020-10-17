package de.schnettler.composepreferences

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import com.tfcporciuncula.flow.FlowSharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val AmbientPreference: ProvidableAmbient<FlowSharedPreferences> =
    ambientOf { error("No preferences found") }

@ExperimentalCoroutinesApi
@Composable
fun ProvidePreferences(
    sharedPreferences: FlowSharedPreferences,
    content: @Composable () -> Unit
) {
    Providers(AmbientPreference provides sharedPreferences) {
        content()
    }
}

val AmbientPreferenceEnabled: ProvidableAmbient<Boolean> =
    ambientOf { true }