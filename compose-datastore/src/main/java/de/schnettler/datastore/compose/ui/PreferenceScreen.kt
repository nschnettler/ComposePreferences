package de.schnettler.datastore.compose.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.accompanist.insets.statusBarsPadding
import de.schnettler.datastore.compose.LocalPreferenceEnabledStatus
import de.schnettler.datastore.compose.model.BasePreferenceItem
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceGroup
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PreferenceScreen(
    items: List<BasePreferenceItem>,
    dataStore: DataStore<Preferences>,
    modifier: Modifier = Modifier,
    statusBarPadding: Boolean = false,
) {
    val dataStoreManager = remember {
        DataStoreManager(dataStore)
    }

    PreferenceScreen(
        items = items,
        modifier = modifier,
        dataStoreManager = dataStoreManager,
        statusBarPadding = statusBarPadding
    )
}

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PreferenceScreen(
    items: List<BasePreferenceItem>,
    modifier: Modifier = Modifier,
    dataStoreManager: DataStoreManager,
    statusBarPadding: Boolean = false,
) {
    val prefs by dataStoreManager.preferenceFlow.collectAsState(initial = null)
    LazyColumn(modifier = modifier) {
        if (statusBarPadding) {
            item { Spacer(modifier = Modifier.statusBarsPadding()) }
        }

        items.forEach { preference ->
            when (preference) {
                // Create Preference Group
                is PreferenceGroup -> {
                    item {
                        PreferenceGroupHeader(title = preference.title)
                    }
                    items(preference.preferenceItems) { item ->
                        CompositionLocalProvider(LocalPreferenceEnabledStatus provides preference.enabled) {
                            PreferenceItem(
                                item = item,
                                prefs = prefs,
                                dataStoreManager = dataStoreManager
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // Create Preference Item
                is PreferenceItem<*> -> item {
                    PreferenceItem(
                        item = preference,
                        prefs = prefs,
                        dataStoreManager = dataStoreManager
                    )
                }
            }
        }
    }
}