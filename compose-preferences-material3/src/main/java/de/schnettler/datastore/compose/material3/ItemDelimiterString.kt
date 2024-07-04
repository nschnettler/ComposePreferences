package de.schnettler.datastore.compose.material3

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun getItemDelimiter(context: Context): String {
    return context.getString(R.string.item_delimiter)
}

@Composable
fun getItemDelimiterInCompose(): String {
    return getItemDelimiter(LocalContext.current)
}