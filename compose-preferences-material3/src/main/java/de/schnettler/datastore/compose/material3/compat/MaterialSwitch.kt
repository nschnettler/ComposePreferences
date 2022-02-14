package de.schnettler.datastore.compose.material3.compat

import androidx.compose.material.ContentAlpha
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

object Material3SwitchDefaults {
    /**
     * Creates a [SwitchColors] that represents the different colors used in a [Switch] in
     * different states.
     *
     * @param checkedThumbColor the color used for the thumb when enabled and checked
     * @param checkedTrackColor the color used for the track when enabled and checked
     * @param checkedTrackAlpha the alpha applied to [checkedTrackColor] and
     * [disabledCheckedTrackColor]
     * @param uncheckedThumbColor the color used for the thumb when enabled and unchecked
     * @param uncheckedTrackColor the color used for the track when enabled and unchecked
     * @param uncheckedTrackAlpha the alpha applied to [uncheckedTrackColor] and
     * [disabledUncheckedTrackColor]
     * @param disabledCheckedThumbColor the color used for the thumb when disabled and checked
     * @param disabledCheckedTrackColor the color used for the track when disabled and checked
     * @param disabledUncheckedThumbColor the color used for the thumb when disabled and unchecked
     * @param disabledUncheckedTrackColor the color used for the track when disabled and unchecked
     */
    @Composable
    fun colors(
        checkedThumbColor: Color = MaterialTheme.colorScheme.secondary,
        checkedTrackColor: Color = checkedThumbColor,
        checkedTrackAlpha: Float = 0.54f,
        uncheckedThumbColor: Color = MaterialTheme.colorScheme.surfaceVariant,
        uncheckedTrackColor: Color = MaterialTheme.colorScheme.onSurface,
        uncheckedTrackAlpha: Float = 0.38f,
        disabledCheckedThumbColor: Color = checkedThumbColor.copy(alpha = ContentAlpha.disabled).compositeOver(MaterialTheme.colorScheme.surface),
        disabledCheckedTrackColor: Color = checkedTrackColor.copy(alpha = ContentAlpha.disabled).compositeOver(MaterialTheme.colorScheme.surface),
        disabledUncheckedThumbColor: Color = uncheckedThumbColor.copy(alpha = ContentAlpha.disabled).compositeOver(MaterialTheme.colorScheme.surface),
        disabledUncheckedTrackColor: Color = uncheckedTrackColor.copy(alpha = ContentAlpha.disabled).compositeOver(MaterialTheme.colorScheme.surface)
    ): SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor.copy(alpha = checkedTrackAlpha),
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor.copy(alpha = uncheckedTrackAlpha),
        disabledCheckedThumbColor = disabledCheckedThumbColor,
        disabledCheckedTrackColor = disabledCheckedTrackColor.copy(alpha = checkedTrackAlpha),
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor.copy(alpha = uncheckedTrackAlpha)
    )
}