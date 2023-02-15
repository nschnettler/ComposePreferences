package de.schnettler.datastore.compose.material3.model

import androidx.compose.runtime.Composable
import de.schnettler.datastore.manager.PreferenceRequest

/**
 * The basic building block that represents an individual setting displayed to a user in the preference hierarchy.
 */
sealed class Preference {
    abstract val title: String
    abstract val enabled: Boolean

    /**
     * A single [Preference] item
     */
    sealed class PreferenceItem<T> : Preference() {
        abstract val summary: String?
        abstract val singleLineTitle: Boolean
        abstract val icon: @Composable (() -> Unit)?

        /**
         * 	A basic [PreferenceItem] that only displays text.
         */
        data class TextPreference(
            override val title: String,
            override val summary: String? = null,
            override val singleLineTitle: Boolean,
            override val icon: @Composable (() -> Unit)? = null,
            override val enabled: Boolean = true,

            val onClick: () -> Unit = {}
        ) : PreferenceItem<String>()

        /**
         * 	A [PreferenceItem] that provides a two-state toggleable option.
         */
        data class SwitchPreference(
            val request: PreferenceRequest<Boolean>,
            override val title: String,
            override val summary: String? = null,
            override val singleLineTitle: Boolean,
            override val icon: @Composable (() -> Unit)? = null,
            override val enabled: Boolean = true,
        ) : PreferenceItem<Boolean>()

        /**
         * 	A [PreferenceItem] that displays a list of entries as a dialog.
         * 	Only one entry can be selected at any given time.
         */
        data class ListPreference(
            val request: PreferenceRequest<String>,
            override val title: String,
            override val summary: String? = null,
            override val singleLineTitle: Boolean,
            override val icon: @Composable (() -> Unit)? = null,
            override val enabled: Boolean = true,

            val entries: Map<String, String>,
        ) : PreferenceItem<String>()

        /**
         * A [PreferenceItem] that displays a list of entries as a dialog.
         * Multiple entries can be selected at the same time.
         */
        data class MultiSelectListPreference(
            val request: PreferenceRequest<Set<String>>,
            override val title: String,
            override val summary: String? = null,
            override val singleLineTitle: Boolean,
            override val icon: @Composable (() -> Unit)? = null,
            override val enabled: Boolean = true,

            val entries: Map<String, String>,
        ) : PreferenceItem<Set<String>>()

        /**
         * A [PreferenceItem] that displays a seekBar and the currently selected value.
         */
        data class SeekBarPreference(
            val request: PreferenceRequest<Float>,
            override val title: String,
            override val summary: String? = null,
            override val singleLineTitle: Boolean,
            override val icon: @Composable (() -> Unit)? = null,
            override val enabled: Boolean = true,

            val valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
            val steps: Int = 0,
            val valueRepresentation: (Float) -> String
        ) : PreferenceItem<Float>()

        /**
         * 	A [PreferenceItem] that displays a list of entries as a DropDownMenu.
         * 	Only one entry can be selected at any given time.
         */
        data class DropDownMenuPreference(
            val request: PreferenceRequest<String>,
            override val title: String,
            override val summary: String? = null,
            override val singleLineTitle: Boolean,
            override val icon: @Composable (() -> Unit)? = null,
            override val enabled: Boolean = true,

            val entries: Map<String, String>,
        ) : PreferenceItem<String>()
    }

    /**
     * A container for multiple [PreferenceItem]s
     */
    data class PreferenceGroup(
        override val title: String,
        override val enabled: Boolean = true,

        val preferenceItems: List<PreferenceItem<out Any>>
    ) : Preference()
}