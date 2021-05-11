package de.schnettler.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.*

@Composable
fun PreferenceDialog(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirm: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.8f),
            elevation = 24.dp,
            shape = MaterialTheme.shapes.medium,
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(64.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = title,
                        style = MaterialTheme.typography.h6
                    )
                }
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    content()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = stringResource(android.R.string.cancel).toUpperCase(Locale.getDefault()))
                    }
                    if (onConfirm != null) {
                        TextButton(onClick = onConfirm) {
                            Text(text = stringResource(android.R.string.ok).toUpperCase(Locale.getDefault()))
                        }
                    }
                }
            }
        }
    }
}