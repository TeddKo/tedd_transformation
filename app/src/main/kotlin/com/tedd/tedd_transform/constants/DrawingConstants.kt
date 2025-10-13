package com.tedd.tedd_transform.constants

import androidx.compose.ui.unit.dp

object DrawingConstants {
    const val RECT_SIZE = 100f
    val CORNER_RADIUS = 12.dp

    object Grid {
        const val MIN = -250f
        const val MAX = 250f
        const val INTERVAL = 50f
    }

    object Style {
        const val AXIS_STROKE_WIDTH = 2f
        const val GRID_STROKE_WIDTH = 1f
        const val RECT_STROKE_WIDTH = 2f
        const val PIVOT_RADIUS = 12f
        const val LABEL_TEXT_SIZE = 24f
        const val LABEL_OFFSET_X = 30f
        const val LABEL_OFFSET_Y = 10f
    }
}