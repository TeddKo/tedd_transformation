package com.tedd.tedd_transform.ui.canvas

import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import com.tedd.tedd_transform.constants.DrawingConstants
import com.tedd.tedd_transform.models.TransformState
import com.tedd.tedd_transform.utils.CoordinateSystem
import com.tedd.tedd_transform.utils.TransformCalculator

fun DrawScope.drawGrid(coordSystem: CoordinateSystem) {
    var tick = DrawingConstants.Grid.MIN.toInt()
    val end = DrawingConstants.Grid.MAX.toInt()
    val step = DrawingConstants.Grid.INTERVAL.toInt()

    while (tick <= end) {
        val gridPos = tick.toFloat()
        val isAxis = tick == 0
        val strokeWidth = if (isAxis) {
            DrawingConstants.Style.AXIS_STROKE_WIDTH
        } else {
            DrawingConstants.Style.GRID_STROKE_WIDTH
        }
        val color = if (isAxis) Color.Black else Color.Gray

        val x = coordSystem.toCanvasX(gridPos)
        drawLine(
            color = color,
            start = Offset(x, coordSystem.toCanvasY(DrawingConstants.Grid.MAX)),
            end = Offset(x, coordSystem.toCanvasY(DrawingConstants.Grid.MIN)),
            strokeWidth = strokeWidth
        )

        val y = coordSystem.toCanvasY(gridPos)
        drawLine(
            color = color,
            start = Offset(coordSystem.toCanvasX(DrawingConstants.Grid.MIN), y),
            end = Offset(coordSystem.toCanvasX(DrawingConstants.Grid.MAX), y),
            strokeWidth = strokeWidth
        )

        tick += step
    }
}

fun DrawScope.drawAxisLabels(coordSystem: CoordinateSystem) {
    val textPaint = Paint().apply {
        color = Color.Black.toArgb()
        textSize = DrawingConstants.Style.LABEL_TEXT_SIZE
        textAlign = Paint.Align.CENTER
    }

    val axisYCanvas = coordSystem.toCanvasY(0f)
    val axisXCanvas = coordSystem.toCanvasX(0f)

    var tick = DrawingConstants.Grid.MIN.toInt()
    val end = DrawingConstants.Grid.MAX.toInt()
    val step = DrawingConstants.Grid.INTERVAL.toInt()
    while (tick <= end) {
        if (tick != 0) {
            val gridPos = tick.toFloat()
            drawContext.canvas.nativeCanvas.drawText(
                gridPos.toInt().toString(),
                coordSystem.toCanvasX(gridPos),
                axisYCanvas + DrawingConstants.Style.LABEL_OFFSET_X,
                textPaint
            )

            textPaint.textAlign = Paint.Align.RIGHT
            drawContext.canvas.nativeCanvas.drawText(
                gridPos.toInt().toString(),
                axisXCanvas - DrawingConstants.Style.LABEL_OFFSET_Y,
                coordSystem.toCanvasY(gridPos) + 8f,
                textPaint
            )
            textPaint.textAlign = Paint.Align.CENTER
        }

        tick += step
    }
}

fun DrawScope.drawRectangle(
    state: TransformState,
    coordSystem: CoordinateSystem
) {
    val vertices = TransformCalculator.calculateTransformedVertices(state, coordSystem)

    val path = Path().apply {
        moveTo(vertices[0].x, vertices[0].y)
        lineTo(vertices[1].x, vertices[1].y)
        lineTo(vertices[2].x, vertices[2].y)
        lineTo(vertices[3].x, vertices[3].y)
        close()
    }

    drawPath(
        path = path,
        color = Color.White
    )

    drawPath(
        path = path,
        color = Color.DarkGray,
        style = Stroke(width = DrawingConstants.Style.RECT_STROKE_WIDTH)
    )
}

fun DrawScope.drawPivotPoint(
    state: TransformState,
    coordSystem: CoordinateSystem
) {
    drawCircle(
        color = Color.Red,
        radius = DrawingConstants.Style.PIVOT_RADIUS,
        center = coordSystem.toCanvas(state.position)
    )
}
