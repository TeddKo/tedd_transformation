package com.tedd.tedd_transform.utils

import androidx.compose.ui.geometry.Offset
import com.tedd.tedd_transform.constants.DrawingConstants

class CoordinateSystem(
    private val canvasWidth: Float,
    private val canvasHeight: Float
) {
    private val coordRange = DrawingConstants.Grid.MAX - DrawingConstants.Grid.MIN
    private val scale = minOf(canvasWidth, canvasHeight) / coordRange

    fun toCanvasX(globalX: Float): Float = (globalX - DrawingConstants.Grid.MIN) * scale
    fun toCanvasY(globalY: Float): Float = canvasHeight - (globalY - DrawingConstants.Grid.MIN) * scale

    fun toCanvas(global: Offset): Offset = Offset(
        x = toCanvasX(global.x),
        y = toCanvasY(global.y)
    )
}
