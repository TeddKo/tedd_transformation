package com.tedd.tedd_transform.ui.state

import androidx.compose.ui.geometry.Offset
import com.tedd.tedd_transform.extensions.toOffsetOrNull

sealed interface TransformationIntent {
    data class InputPosition(val position: String) : TransformationIntent
    data class InputRotation(val rotation: String) : TransformationIntent
    data class InputPivot(val pivot: String) : TransformationIntent
    data object Apply : TransformationIntent
}

class TransformIntent(private val controller: TransformControllerStateHolder) {
    fun intent(intent: TransformationIntent) {
        when (intent) {
            is TransformationIntent.InputPosition -> controller.updateDraft { it.copy(position = intent.position) }

            is TransformationIntent.InputRotation -> controller.updateDraft { it.copy(rotation = intent.rotation) }

            is TransformationIntent.InputPivot -> controller.updateDraft { it.copy(pivot = intent.pivot) }

            TransformationIntent.Apply -> {
                val draft = controller.draftValue
                val newPosition = draft.position.toOffsetOrNull() ?: Offset.Zero
                val newRotation = draft.rotation.toFloatOrNull() ?: 0f
                val newPivot = draft.pivot.toOffsetOrNull() ?: Offset.Zero
                controller.updateApplied { current ->
                    current.copy(position = newPosition, rotation = newRotation, pivot = newPivot)
                }
            }
        }
    }
}
