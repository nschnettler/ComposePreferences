package de.schnettler.composepreferences

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import de.schnettler.composepreferences.ui.ComposePreferencesTheme
import de.schnettler.datastorepreferences.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePreferencesTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Compose Preferences") }
                        )
                    }
                ) {
                    PreferenceScreen(
                        items = listOf(
                            StringPreferenceItem(
                                title = "Text Preference",
                                summary = "No value entered",
                                key = "pref_string",
                                singleLineTitle = true,
                                icon = Icons.Outlined.Edit
                            ),
                            SwitchPreferenceItem(
                                title = "Switch Preference",
                                summary = "A preference with a switch.",
                                key = "pref_switch",
                                singleLineTitle = true,
                                icon = Icons.Outlined.Check,
                            ),
                            SingleListPreferenceItem(
                                title = "List Preference",
                                summary = "Select one item from a list in a dialog",
                                key = "pref_list",
                                singleLineTitle = true,
                                icon = Icons.Outlined.List,
                                entries = mapOf(
                                    "key1" to "Item1",
                                    "key2" to "Item2"
                                ),
                            ),
                            MultiListPreferenceItem(
                                title = "MultiSelect List Preference",
                                summary = "Select multiple items from a list in a dialog",
                                key = "pref_multi_list",
                                singleLineTitle = true,
                                icon = Icons.Outlined.List,
                                entries = mapOf(
                                    "key1" to "Item1",
                                    "key2" to "Item2"
                                ),
                            ),
                            SeekbarPreferenceItem(
                                title = "Seekbar Preference",
                                summary = "Select a value on a seekbar",
                                key = "pref_seek",
                                defaultValue = 50F,
                                singleLineTitle = true,
                                icon = Icons.Outlined.AccountCircle,
                                steps = 4,
                                valueRange = 50F..100F,
                                valueRepresentation = { value -> "${value.roundToInt()} %" }
                            )
                        )
                    )
                }
            }
        }
    }
}

//@ExperimentalMaterialApi
//@Composable
//fun PreferenceScreen() {
//    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
//    ) {
//        var switchValue by remember { mutableStateOf(false) }
//        SwitchPreference(
//            title = "Switch Preference",
//            summary = "A preference with a switch.",
//            key = "pref_switch",
//            singleLineTitle = true,
//            icon = Icons.Outlined.Warning,
//        )
//
//
//
//        PreferenceGroup(title = "List Group", enabled = true) {
//            ListPreference(
//                title = "List Preference",
//                summary = "Select one item from a list in a dialog",
//                key = "pref_list",
//                singleLineTitle = true,
//                icon = Icons.Outlined.Warning,
//                entries = mapOf(
//                    "key1" to "Item1",
//                    "key2" to "Item2"
//                ),
//            )
//            MultiSelectListPreference(
//                title = "MultiSelect List Preference",
//                summary = "Select multiple items from a list in a dialog",
//                key = "pref_multi_list",
//                singleLineTitle = true,
//                icon = Icons.Outlined.Warning,
//                entries = mapOf(
//                    "key1" to "Item1",
//                    "key2" to "Item2"
//                ),
//            )
//        }
//
//        PreferenceGroup(title = "Seekbar Group") {
//            SeekBarPreference(
//                title = "Seekbar Preference",
//                summary = "Select a value on a seekbar",
//                key = "pref_seek",
//                defaultValue = 50F,
//                singleLineTitle = true,
//                icon = Icons.Outlined.Warning,
//                steps = 4,
//                valueRange = 50F..100F,
//                valueRepresentation = { value -> "${value.roundToInt()} %" }
//            )
//        }
//    }
//}