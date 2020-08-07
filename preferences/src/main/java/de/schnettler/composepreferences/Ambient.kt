package de.schnettler.composepreferences

import androidx.compose.ProvidableAmbient
import androidx.compose.ambientOf
import com.tfcporciuncula.flow.FlowSharedPreferences

val AmbientPreferences: ProvidableAmbient<FlowSharedPreferences> =
    ambientOf { error("No preferences found") }