package de.schnettler.datastorepreferences

import androidx.compose.runtime.*
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val LocalDataStoreManager: ProvidableCompositionLocal<DataStoreManager> =
    compositionLocalOf(structuralEqualityPolicy()) { error("No preferences found") }


@ExperimentalCoroutinesApi
@Composable
fun ProvideDataStoreManager(
    dataStoreManager: DataStoreManager,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDataStoreManager provides dataStoreManager) {
        content()
    }
}