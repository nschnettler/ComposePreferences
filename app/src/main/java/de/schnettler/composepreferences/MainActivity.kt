package de.schnettler.composepreferences

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import de.schnettler.composepreferences.ui.ComposePreferencesTheme
import de.schnettler.datastore.compose.PreferenceScreen
import de.schnettler.datastore.compose.model.BasePreferenceItem
import kotlin.math.roundToInt

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val localDataStore = this.dataStore

        val listGroup = BasePreferenceItem.PreferenceGroup("List Group", false, listOf(
                BasePreferenceItem.PreferenceItem.RadioBoxListPreferenceItem(
                    ListPrefExample,
                    title = "List Preference",
                    summary = "Select one item from a list in a dialog",
                    singleLineTitle = true,
                    icon = Icons.Outlined.Warning,
                    entries = mapOf(
                        "key1" to "Item1",
                        "key2" to "Item2"
                    ),
                ),
                BasePreferenceItem.PreferenceItem.CheckBoxListPreferenceItem(
                    MultiPrefExample,
                    title = "MultiSelect List Preference",
                    summary = "Select multiple items from a list in a dialog",
                    singleLineTitle = true,
                    icon = Icons.Outlined.Warning,
                    entries = mapOf(
                        "key1" to "Item1",
                        "key2" to "Item2"
                    ),
                ),
            )
        )

        setContent {
            ComposePreferencesTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Compose Preferences") }
                        )
                    },
                    content = {
                        PreferenceScreen(
                            items = listOf(
                                BasePreferenceItem.PreferenceItem.SwitchPreferenceItem(
                                    SwitchPrefExample,
                                    title = "Switch Preference",
                                    summary = "A preference with a switch.",
                                    singleLineTitle = true,
                                    icon = Icons.Outlined.Warning,
                                ),
                                listGroup,
                                BasePreferenceItem.PreferenceItem.SeekBarPreferenceItem(
                                    SeekPrefExample,
                                    title = "Seekbar Preference",
                                    summary = "Select a value on a seekbar",
                                    singleLineTitle = true,
                                    icon = Icons.Outlined.Warning,
                                    steps = 4,
                                    valueRange = 50F..100F,
                                    valueRepresentation = { value -> "${value.roundToInt()} %" }
                                )
                            ),
                            dataStore = localDataStore
                        )
                    }
                )
            }
        }
    }
}