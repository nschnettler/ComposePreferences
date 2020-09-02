package de.schnettler.composepreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.tfcporciuncula.flow.FlowSharedPreferences
import de.schnettler.composepreferences.ui.ComposePreferencesTheme
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = FlowSharedPreferences(this.defaultSharedPrefs())

        setContent {
            ComposePreferencesTheme {
                ProvidePreferences(sharedPreferences = sharedPreferences) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Compose Preferences") }
                            )
                        },
                        bodyContent = {
                            PreferenceScreen()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PreferenceScreen() {
    ScrollableColumn {
        SwitchPreference(
            title = "Switch Preference",
            summary = "A preference with a switch.",
            key = "pref_switch",
            singleLineTitle = true,
            icon = Icons.Outlined.Warning,
        )



        PreferenceGroup(title = "List Group", enabled = false) {
            ListPreference(
                title = "List Preference",
                summary = "Select one item from a list in a dialog",
                key = "pref_list",
                singleLineTitle = true,
                icon = Icons.Outlined.Warning,
                entries = mapOf(
                    "key1" to "Item1",
                    "key2" to "Item2"
                ),
            )
            MultiSelectListPreference(
                title = "MultiSelect List Preference",
                summary = "Select multiple items from a list in a dialog",
                key = "pref_multi_list",
                singleLineTitle = true,
                icon = Icons.Outlined.Warning,
                entries = mapOf(
                    "key1" to "Item1",
                    "key2" to "Item2"
                ),
            )
        }

        PreferenceGroup(title = "Seekbar Group") {
            SeekBarPreference(
                title = "Seekbar Preference",
                summary = "Select a value on a seekbar",
                key = "pref_seek",
                defaultValue = 50F,
                singleLineTitle = true,
                icon = Icons.Outlined.Warning,
                steps = 4,
                valueRange = 50F..100F,
                valueRepresentation = { value -> "${value.roundToInt()} %" }
            )
        }
    }
}