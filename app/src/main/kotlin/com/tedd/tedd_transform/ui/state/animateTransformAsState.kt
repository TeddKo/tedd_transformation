
package com.tedd.tedd_transform.ui.state

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.tedd.tedd_transform.models.TransformState

@Composable
fun animateTransformAsState(targetState: TransformState): TransformState {
    val position by animateOffsetAsState(targetValue = targetState.position, label = "position")
    val rotation by animateFloatAsState(targetValue = targetState.rotation, label = "rotation")
    val pivot by animateOffsetAsState(targetValue = targetState.pivot, label = "pivot")

    return TransformState(
        position = position,
        rotation = rotation,
        pivot = pivot
    )
}
