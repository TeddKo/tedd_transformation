package com.tedd.tedd_transform.models

import androidx.annotation.StringRes
import androidx.compose.ui.geometry.Offset
import com.tedd.tedd_transform.R

data class TransformState(
    val position: Offset = Offset.Zero,
    val rotation: Float = 0f,
    val pivot: Offset = Offset.Zero
)

sealed class VertexType(@StringRes val stringResId: Int) {
    data object LeftTop : VertexType(R.string.vertex_left_top)
    data object RightTop : VertexType(R.string.vertex_right_top)
    data object RightBottom : VertexType(R.string.vertex_right_bottom)
    data object LeftBottom : VertexType(R.string.vertex_left_bottom)
}

data class Vertex(
    val type: VertexType,
    val x: Float,
    val y: Float
)