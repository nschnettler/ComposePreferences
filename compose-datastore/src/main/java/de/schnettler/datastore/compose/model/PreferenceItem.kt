package de.schnettler.datastore.compose.model

import androidx.compose.ui.graphics.vector.ImageVector
import de.schnettler.datastore.manager.PreferenceRequest

sealed class BasePreferenceItem() {
    abstract val title: String
    abstract val enabled: Boolean

    sealed class PreferenceItem<T> : BasePreferenceItem() {
        abstract val summary: String
        abstract val singleLineTitle: Boolean
        abstract val icon: ImageVector

        data class BasicPreferenceItem(
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: ImageVector,
            override val enabled: Boolean = true,

            val onClick: () -> Unit = {}
        ) : PreferenceItem<String>()

        data class SwitchPreferenceItem(
            val request: PreferenceRequest<Boolean>,
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: ImageVector,
            override val enabled: Boolean = true,
        ) : PreferenceItem<Boolean>()

        data class RadioBoxListPreferenceItem(
            val request: PreferenceRequest<String>,
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: ImageVector,
            override val enabled: Boolean = true,

            val entries: Map<String, String>,
        ) : PreferenceItem<String>()

        data class CheckBoxListPreferenceItem(
            val request: PreferenceRequest<Set<String>>,
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: ImageVector,
            override val enabled: Boolean = true,

            val entries: Map<String, String>,
        ) : PreferenceItem<Set<String>>()

        data class SeekBarPreferenceItem(
            val request: PreferenceRequest<Float>,
            override val title: String,
            override val summary: String,
            override val singleLineTitle: Boolean,
            override val icon: ImageVector,
            override val enabled: Boolean = true,

            val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
            val steps: Int = 0,
            val valueRepresentation: (Float) -> String
        ) : PreferenceItem<Float>()

    }

    data class PreferenceGroup(
        override val title: String,
        override val enabled: Boolean = true,

        val preferenceItems: List<PreferenceItem<out Any>>
    ) : BasePreferenceItem()
}