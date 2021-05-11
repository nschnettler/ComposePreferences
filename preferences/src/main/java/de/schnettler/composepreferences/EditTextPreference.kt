package de.schnettler.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.util.*

@ExperimentalMaterialApi
@Composable
fun EditTextPreference(
    title: String,
    summary: String,
    value: String?,
    onValueChanged: (String) -> Unit = {},
    singleLineTitle: Boolean,
    icon: ImageVector,
    enabled: Boolean = true,
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }

    Preference(
        title = title,
        summary = value ?: summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true },
    )

    if (showDialog.value) {
        EditTextDialog(
            title = title,
            value = value ?: "",
            onDismissRequest = closeDialog,
            onConfirm = onValueChanged
        )
    }
}

@Composable
fun EditTextDialog(
    title: String,
    value: String,
    onDismissRequest: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    var textValue: TextFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }
    PreferenceDialog(
        onDismissRequest = onDismissRequest,
        title = title,
        onConfirm = {
            onConfirm(textValue.text)
            onDismissRequest()
        }
    ) {
        val focusRequester = FocusRequester()
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = textValue,
            onValueChange = { textValue = it },
            singleLine = true
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}