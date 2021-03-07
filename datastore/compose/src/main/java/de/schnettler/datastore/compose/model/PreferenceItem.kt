package de.schnettler.datastore.compose.model

import androidx.compose.ui.graphics.vector.ImageVector
import de.schnettler.datastore.manager.PreferenceMetaData

interface BasePreferenceItem

interface PreferenceItem<T> : BasePreferenceItem {
    val title: String
    val entry: PreferenceMetaData<T>
    val summary: String
    val singleLineTitle: Boolean
    val icon: ImageVector
    val enabled: Boolean
}

interface ListPreferenceItem<T> : PreferenceItem<T> {
    val entries: Map<String, String>
}

data class SwitchPreferenceItem(
    override val entry: PreferenceMetaData<Boolean>,
    override val title: String,
    override val summary: String,
    override val singleLineTitle: Boolean,
    override val icon: ImageVector,
    override val enabled: Boolean = true,
): PreferenceItem<Boolean>

data class SingleListPreferenceItem(
    override val entry: PreferenceMetaData<String>,
    override val title: String,
    override val summary: String,
    override val singleLineTitle: Boolean,
    override val icon: ImageVector,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
) : ListPreferenceItem<String>

data class MultiListPreferenceItem(
    override val entry: PreferenceMetaData<Set<String>>,
    override val title: String,
    override val summary: String,
    override val singleLineTitle: Boolean,
    override val icon: ImageVector,
    override val enabled: Boolean = true,
    override val entries: Map<String, String>,
    val defaultValue: Set<String> = emptySet()
) : ListPreferenceItem<Set<String>>

data class SeekbarPreferenceItem(
    override val entry: PreferenceMetaData<Float>,
    override val title: String,
    override val summary: String,
    override val singleLineTitle: Boolean,
    override val icon: ImageVector,
    override val enabled: Boolean = true,
    val defaultValue: Float = 0F,
    val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    val steps: Int = 0,
    val valueRepresentation: (Float) -> String
) : PreferenceItem<Float>

data class PreferenceGroup(
    val title: String,
    val enabled: Boolean = true,
    val content: List<PreferenceItem<*>>
) : BasePreferenceItem