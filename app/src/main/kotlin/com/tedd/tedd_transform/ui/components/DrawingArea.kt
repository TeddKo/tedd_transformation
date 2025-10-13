package com.tedd.tedd_transform.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tedd.tedd_transform.constants.DrawingConstants
import com.tedd.tedd_transform.models.TransformState
import com.tedd.tedd_transform.ui.canvas.drawAxisLabels
import com.tedd.tedd_transform.ui.canvas.drawGrid
import com.tedd.tedd_transform.ui.canvas.drawPivotPoint
import com.tedd.tedd_transform.ui.canvas.drawRectangle
import com.tedd.tedd_transform.utils.CoordinateSystem

@Composable
fun DrawingArea(
    modifier: Modifier = Modifier,
    state: TransformState
) {
    Canvas(
        modifier = modifier
            .clip(shape = RoundedCornerShape(DrawingConstants.CORNER_RADIUS))
            .background(color = Color.LightGray)
            .border(2.dp, Color.Black, shape = RoundedCornerShape(DrawingConstants.CORNER_RADIUS))
            .padding(16.dp)
    ) {
        val coordSystem = CoordinateSystem(size.width, size.height)
        drawGrid(coordSystem)
        drawAxisLabels(coordSystem)
        drawRectangle(state, coordSystem)
        drawPivotPoint(state, coordSystem)
    }
}