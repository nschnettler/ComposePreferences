package de.schnettler.composepreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.preference.PreferenceManager
import androidx.ui.tooling.preview.Preview
import com.tfcporciuncula.flow.FlowSharedPreferences
import de.schnettler.composepreferences.ui.ComposePreferencesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePreferencesTheme {
                // A surface container using the 'background' color from the theme
                Providers(AmbientPreferences provides FlowSharedPreferences(PreferenceManager.getDefaultSharedPreferences(this))) {
                    Surface(color = MaterialTheme.colors.background) {
                        ScrollableColumn {
                            SwitchPreference(
                                title = "Switch Preference",
                                summary = "A preference with a switch.",
                                key = "pref_switch",
                                singleLineTitle = true,
                                icon = Icons.Outlined.Warning
                            )
                            ListPreference(
                                title = "List Preference",
                                summary = "Select one item from a list in a dialog",
                                key = "pref_list",
                                singleLineTitle = true,
                                icon = Icons.Outlined.Warning,
                                entries = mapOf(
                                    "key1" to "Item1",
                                    "key2" to "Item2"
                                )
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
                                )
                            )
                            SeekBarPreference(
                                title = "Seekbar Preference",
                                summary = "Select a value on a seekbar",
                                key = "pref_seek",
                                defaultValue = 50F,
                                singleLineTitle = true,
                                icon = Icons.Outlined.Warning,
                                steps = 4,
                                valueRange = 50F..100F
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePreferencesTheme {
        Greeting("Android")
    }
}