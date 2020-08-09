package de.schnettler.composepreferences

import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.runtime.ambientOf
import com.tfcporciuncula.flow.FlowSharedPreferences

val AmbientPreferences: ProvidableAmbient<FlowSharedPreferences> =
    ambientOf { error("No preferences found") }