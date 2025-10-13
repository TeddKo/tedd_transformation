package com.tedd.tedd_transform.utils

import androidx.compose.ui.geometry.Offset
import com.tedd.tedd_transform.constants.DrawingConstants
import com.tedd.tedd_transform.models.TransformState
import com.tedd.tedd_transform.models.Vertex
import com.tedd.tedd_transform.models.VertexType
import kotlin.math.cos
import kotlin.math.sin

object TransformCalculator {
    fun calculateVertices(state: TransformState): List<Vertex> {
        val rectVertices = listOf(
            Offset(0f, DrawingConstants.RECT_SIZE),
            Offset(DrawingConstants.RECT_SIZE, DrawingConstants.RECT_SIZE),
            Offset(DrawingConstants.RECT_SIZE, 0f),
            Offset.Zero
        )

        val vertexTypes = listOf(
            VertexType.LeftTop,
            VertexType.RightTop,
            VertexType.RightBottom,
            VertexType.LeftBottom
        )

        val angleRad = Math.toRadians(state.rotation.toDouble())
        val cosAngle = cos(angleRad).toFloat()
        val sinAngle = sin(angleRad).toFloat()

        return rectVertices.mapIndexed { index, vertex ->
            val rotated = vertex.rotateAround(state.pivot, cosAngle, sinAngle)
            val global = rotated - state.pivot + state.position

            Vertex(
                type = vertexTypes[index],
                x = global.x,
                y = global.y
            )
        }
    }

    fun calculateTransformedVertices(
        state: TransformState,
        coordSystem: CoordinateSystem
    ): List<Offset> {
        val rectVertices = listOf(
            Offset(0f, DrawingConstants.RECT_SIZE),
            Offset(DrawingConstants.RECT_SIZE, DrawingConstants.RECT_SIZE),
            Offset(DrawingConstants.RECT_SIZE, 0f),
            Offset.Zero
        )

        val angleRad = Math.toRadians(state.rotation.toDouble())
        val cosAngle = cos(angleRad).toFloat()
        val sinAngle = sin(angleRad).toFloat()

        return rectVertices.map { vertex ->
            val rotated = vertex.rotateAround(state.pivot, cosAngle, sinAngle)
            val global = rotated - state.pivot + state.position
            coordSystem.toCanvas(global)
        }
    }

    private fun Offset.rotateAround(
        pivot: Offset,
        cosAngle: Float,
        sinAngle: Float
    ): Offset {
        val relX = x - pivot.x
        val relY = y - pivot.y

        val rotatedX = relX * cosAngle - relY * sinAngle
        val rotatedY = relX * sinAngle + relY * cosAngle

        return Offset(rotatedX + pivot.x, rotatedY + pivot.y)
    }
}
