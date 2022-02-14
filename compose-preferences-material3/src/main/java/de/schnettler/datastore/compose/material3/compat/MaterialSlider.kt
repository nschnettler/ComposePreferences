package de.schnettler.datastore.compose.material3.compat

import androidx.compose.material.ContentAlpha
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.SliderDefaults.DisabledActiveTrackAlpha
import androidx.compose.material.SliderDefaults.DisabledInactiveTrackAlpha
import androidx.compose.material.SliderDefaults.DisabledTickAlpha
import androidx.compose.material.SliderDefaults.InactiveTrackAlpha
import androidx.compose.material.SliderDefaults.TickAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

/**
 * Object to hold defaults used by [Slider]
 */
object Material3SliderDefaults {

    /**
     * Creates a [SliderColors] that represents the different colors used in parts of the
     * [Slider] in different states.
     *
     * For the name references below the words "active" and "inactive" are used. Active part of
     * the slider is filled with progress, so if slider's progress is 30% out of 100%, left (or
     * right in RTL) 30% of the track will be active, the rest is not active.
     *
     * @param thumbColor thumb color when enabled
     * @param disabledThumbColor thumb colors when disabled
     * @param activeTrackColor color of the track in the part that is "active", meaning that the
     * thumb is ahead of it
     * @param inactiveTrackColor color of the track in the part that is "inactive", meaning that the
     * thumb is before it
     * @param disabledActiveTrackColor color of the track in the "active" part when the Slider is
     * disabled
     * @param disabledInactiveTrackColor color of the track in the "inactive" part when the
     * Slider is disabled
     * @param activeTickColor colors to be used to draw tick marks on the active track, if `steps`
     * is specified
     * @param inactiveTickColor colors to be used to draw tick marks on the inactive track, if
     * `steps` are specified on the Slider is specified
     * @param disabledActiveTickColor colors to be used to draw tick marks on the active track
     * when Slider is disabled and when `steps` are specified on it
     * @param disabledInactiveTickColor colors to be used to draw tick marks on the inactive part
     * of the track when Slider is disabled and when `steps` are specified on it
     */
    @Composable
    fun colors(
        thumbColor: Color = MaterialTheme.colorScheme.primary,
        disabledThumbColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled).compositeOver(MaterialTheme.colorScheme.surfaceVariant),
        activeTrackColor: Color = MaterialTheme.colorScheme.primary,
        inactiveTrackColor: Color = activeTrackColor.copy(alpha = InactiveTrackAlpha),
        disabledActiveTrackColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = DisabledActiveTrackAlpha),
        disabledInactiveTrackColor: Color = disabledActiveTrackColor.copy(alpha = DisabledInactiveTrackAlpha),
        activeTickColor: Color = contentColorFor(activeTrackColor).copy(alpha = TickAlpha),
        inactiveTickColor: Color = activeTrackColor.copy(alpha = TickAlpha),
        disabledActiveTickColor: Color = activeTickColor.copy(alpha = DisabledTickAlpha),
        disabledInactiveTickColor: Color = disabledInactiveTrackColor.copy(alpha = DisabledTickAlpha)
    ): SliderColors = SliderDefaults.colors(
        thumbColor = thumbColor,
        disabledThumbColor = disabledThumbColor,
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        activeTickColor = activeTickColor,
        inactiveTickColor = inactiveTickColor,
        disabledActiveTickColor = disabledActiveTickColor,
        disabledInactiveTickColor = disabledInactiveTickColor
    )
}