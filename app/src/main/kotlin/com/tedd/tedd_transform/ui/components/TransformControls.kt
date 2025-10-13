package com.tedd.tedd_transform.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tedd.tedd_transform.R
import com.tedd.tedd_transform.constants.DrawingConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TransformControls(
    modifier: Modifier = Modifier,
    position: String,
    rotation: String,
    pivot: String,
    onPositionChange: (String) -> Unit,
    onRotationChange: (String) -> Unit,
    onPivotChange: (String) -> Unit,
    onApply: () -> Unit,
    bringIntoViewRequester: BringIntoViewRequester,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = modifier.bringIntoViewRequester(bringIntoViewRequester),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InputField(
            value = position,
            onValueChange = onPositionChange,
            label = stringResource(R.string.label_position),
            placeholder = { Text("0, 0", color = Color.Black.copy(alpha = .5f)) },
            onFocusChanged = { isFocused ->
                if (isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
        )

        InputField(
            value = rotation,
            onValueChange = onRotationChange,
            label = stringResource(R.string.label_rotation),
            placeholder = { Text("0", color = Color.Black.copy(alpha = .5f)) },
            onFocusChanged = { isFocused ->
                if (isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
        )

        InputField(
            value = pivot,
            onValueChange = onPivotChange,
            label = stringResource(R.string.label_pivot),
            placeholder = { Text("0, 0", color = Color.Black.copy(alpha = .5f)) },
            onFocusChanged = { isFocused ->
                if (isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
        )

        ApplyButton(onClick = onApply)
    }
}

@Composable
private fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: @Composable (() -> Unit)? = null,
    onFocusChanged: (Boolean) -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                onFocusChanged(focusState.isFocused)
            },
        shape = RoundedCornerShape(DrawingConstants.CORNER_RADIUS),
        singleLine = true,
        placeholder = placeholder
    )
}

@Composable
private fun ApplyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = RoundedCornerShape(DrawingConstants.CORNER_RADIUS)
    ) {
        Text(
            text = stringResource(R.string.button_apply),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
