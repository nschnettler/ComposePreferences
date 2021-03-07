package de.schnettler.datastore.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.datastore.compose.model.BasePreferenceItem
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceGroup
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem
import de.schnettler.datastore.compose.ui.GroupHeader
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PreferenceScreen(
    items: List<BasePreferenceItem>,
    modifier: Modifier = Modifier,
    statusBarPadding: Boolean = false,
) {
    val dataStore = LocalDataStoreManager.current
    val prefs by dataStore.preferenceFlow.collectAsState(initial = null)

    LazyColumn(modifier = modifier) {
        if (statusBarPadding) {
            item { Spacer(modifier = Modifier.statusBarsPadding()) }
        }

        items.forEach { preference ->
            when (preference) {
                is PreferenceGroup -> {
                    item {
                        GroupHeader(title = preference.title)
                    }
                    items(preference.preferenceItems) { item ->
                        CompositionLocalProvider(LocalPreferenceEnabledStatus provides preference.enabled) {
                            PreferenceItemEntry(item, prefs)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                is PreferenceItem<*> -> item {
                    PreferenceItemEntry(item = preference, prefs = prefs)
                }
            }
        }
    }
}