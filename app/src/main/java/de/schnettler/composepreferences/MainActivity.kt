package de.schnettler.composepreferences

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.schnettler.composepreferences.ui.ComposePreferencesTheme
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceGroup
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.material3.widget.PreferenceIcon
import de.schnettler.datastore.compose.material3.PreferenceScreen
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePreferencesTheme {
                AppPreferenceScreen()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppPreferenceScreen() {
    val context = LocalContext.current
    val dataStore = context.dataStore
    val dataStoreManager = remember { DataStoreManager(dataStore) }
    val scope = rememberCoroutineScope()
    val switchPreference by dataStoreManager.getPreferenceFlow(SwitchPrefExample)
        .collectAsState(initial = false)

    val listGroup = PreferenceGroup(
        title = "List Group",
        enabled = switchPreference,
        preferenceItems = listOf(
            PreferenceItem.ListPreference(
                request = ListPrefExample,
                title = "List Preference",
                summary = "Select one item from a list in a dialog",
                singleLineTitle = true,
                icon = { PreferenceIcon(icon = Icons.Outlined.Warning) },
                entries = mapOf(
                    "key1" to "Item1",
                    "key2" to "Item2",
                    "key3" to "Item3",
                    "key4" to "Item4",
                    "key5" to "Item5",
                    "key6" to "Item6",
                    "key7" to "Item7",
                    "key8" to "Item8",
                    "key9" to "Item9",
                    "key10" to "Item10",
                    "key11" to "Item11",
                    "key12" to "Item12",
                    "key13" to "Item13",
                    "key14" to "Item14",
                    "key15" to "Item15",
                    "key16" to "Item16",
                    "key17" to "Item17",
                    "key18" to "Item18",
                    "key19" to "Item19",
                    "key20" to "Item20"
                )
            ),
            PreferenceItem.MultiSelectListPreference(
                request = MultiPrefExample,
                title = "MultiSelect List Preference",
                summary = "Select multiple items from a list in a dialog",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                entries = mapOf(
                    "key1" to "Item1",
                    "key2" to "Item2",
                    "key3" to "Item3",
                    "key4" to "Item4",
                    "key5" to "Item5",
                    "key6" to "Item6",
                    "key7" to "Item7",
                    "key8" to "Item8",
                    "key9" to "Item9",
                    "key10" to "Item10",
                    "key11" to "Item11",
                    "key12" to "Item12",
                    "key13" to "Item13",
                    "key14" to "Item14",
                    "key15" to "Item15",
                    "key16" to "Item16",
                    "key17" to "Item17",
                    "key18" to "Item18",
                    "key19" to "Item19",
                    "key20" to "Item20"
                )
            ),
            PreferenceItem.DropDownMenuPreference(
                request = DropDownPrefExample,
                title = "DropDown Menu Preference",
                summary = "Select an item from a dropdown menu",
                singleLineTitle = true,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                entries = mapOf(
                    "key1" to "Item1",
                    "key2" to "Item2",
                    "key3" to "Item3",
                    "key4" to "Item4",
                    "key5" to "Item5",
                    "key6" to "Item6",
                    "key7" to "Item7",
                    "key8" to "Item8",
                    "key9" to "Item9",
                    "key10" to "Item10",
                    "key11" to "Item11",
                    "key12" to "Item12",
                    "key13" to "Item13",
                    "key14" to "Item14",
                    "key15" to "Item15",
                    "key16" to "Item16",
                    "key17" to "Item17",
                    "key18" to "Item18",
                    "key19" to "Item19",
                    "key20" to "Item20"
                )
            )
        )
    )

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Compose Preferences") }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        PreferenceScreen(
            items = listOf(
                PreferenceItem.SwitchPreference(
                    request = SwitchPrefExample,
                    title = "Switch Preference",
                    summary = "A preference with a switch.",
                    singleLineTitle = true,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                ),
                listGroup,
                PreferenceItem.SeekBarPreference(
                    request = SeekPrefExample,
                    title = "Seekbar Preference",
                    summary = "Select a value on a seekbar",
                    singleLineTitle = true,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    steps = 4,
                    valueRange = 50F..100F,
                    valueRepresentation = { value -> "${value.roundToInt()} %" }
                ),
                PreferenceItem.TextPreference(
                    title = "Clear Preferences",
                    summary = "Remove all saved preferences",
                    singleLineTitle = true,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    enabled = true,
                    onClick = {
                        scope.launch { dataStoreManager.clearPreferences() }
                    }
                )
            ),
            dataStore = dataStore,
            contentPadding = innerPadding
        )
    }
}