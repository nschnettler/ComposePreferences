package de.schnettler.datastorepreferences

import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import androidx.datastore.preferences.preferencesSetKey

interface BasePreferenceItem

interface PreferenceItem<T> : BasePreferenceItem {
    val title: String
    val summary: String
    val key: String
    val singleLineTitle: Boolean
    val icon: VectorAsset
    val enabled: Boolean
}

interface ListPreferenceItem : PreferenceItem<String> {
    val entries: Map<String, String>
}

data class SwitchPreferenceItem(
    override val title: String,
    override val summary: String,
    override val key: String,
    override val singleLineTitle: Boolean,
    override val icon: VectorAsset,
    override val enabled: Boolean = true,
    val defaultValue: Boolean = false,
): PreferenceItem<Boolean> {
    val prefKey = preferencesKey<Boolean>(key)
}

data class SingleListPreferenceItem(
    override val title: String,
    override val summary: String,
    override val key: String,
    override val singleLineTitle: Boolean,
    override val icon: VectorAsset,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val defaultValue: String = "",
) : ListPreferenceItem {
    val prefKey = preferencesKey<String>(key)
}

data class MultiListPreferenceItem(
    override val title: String,
    override val summary: String,
    override val key: String,
    override val singleLineTitle: Boolean,
    override val icon: VectorAsset,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val defaultValue: Set<String> = emptySet()
) : ListPreferenceItem {
    val prefKey = preferencesSetKey<String>(key)
}

data class SeekbarPreferenceItem(
    override val title: String,
    override val summary: String,
    override val key: String,
    override val singleLineTitle: Boolean,
    override val icon: VectorAsset,
    override val enabled: Boolean = true,
    val defaultValue: Float = 0F,
    val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val steps: Int = 0,
    val valueRepresentation: (Float) -> String
) : PreferenceItem<Float> {
    val prefKey = preferencesKey<Float>(key)
}

data class PreferenceGroup(
    val title: String,
    val enabled: Boolean = true,
    val content: List<PreferenceItem<*>>
) : BasePreferenceItem