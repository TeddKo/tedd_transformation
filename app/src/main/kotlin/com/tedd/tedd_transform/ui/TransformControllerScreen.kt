package com.tedd.tedd_transform.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tedd.tedd_transform.R
import com.tedd.tedd_transform.ui.components.DrawingArea
import com.tedd.tedd_transform.ui.components.TransformControls
import com.tedd.tedd_transform.ui.components.VertexCoordinatesDisplay
import com.tedd.tedd_transform.ui.state.TransformIntent
import com.tedd.tedd_transform.ui.state.TransformationIntent
import com.tedd.tedd_transform.ui.state.animateTransformAsState
import com.tedd.tedd_transform.ui.state.rememberTransformControllerState
import com.tedd.tedd_transform.utils.TransformCalculator

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TransformControllerScreen(modifier: Modifier = Modifier) {
    val controllerState = rememberTransformControllerState()
    val transformIntent = remember(controllerState) { TransformIntent(controllerState) }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(scrollState)
            .imeNestedScroll()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.transform_controller_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        val animatedState = animateTransformAsState(targetState = controllerState.value)

        DrawingArea(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 1f),
            state = animatedState
        )

        VertexCoordinatesDisplay(
            modifier = Modifier.fillMaxWidth(),
            vertices = TransformCalculator.calculateVertices(controllerState.value)
        )

        TransformControls(
            modifier = Modifier.fillMaxWidth(),
            position = controllerState.draftValue.position,
            rotation = controllerState.draftValue.rotation,
            pivot = controllerState.draftValue.pivot,
            onPositionChange = { transformIntent.intent(TransformationIntent.InputPosition(it)) },
            onRotationChange = { transformIntent.intent(TransformationIntent.InputRotation(it)) },
            onPivotChange = { transformIntent.intent(TransformationIntent.InputPivot(it)) },
            onApply = { transformIntent.intent(TransformationIntent.Apply) },
            bringIntoViewRequester = bringIntoViewRequester,
            coroutineScope = coroutineScope
        )
    }
}