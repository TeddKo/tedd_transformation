package com.tedd.tedd_transform.extensions

import androidx.compose.ui.geometry.Offset

fun String.toOffsetOrNull(): Offset? {
    val parts = split(",").map { it.trim() }
    if (parts.size != 2) return null

    val x = parts[0].toFloatOrNull() ?: return null
    val y = parts[1].toFloatOrNull() ?: return null

    return Offset(x, y)
}