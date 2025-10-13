package com.tedd.tedd_transform.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Offset
import com.tedd.tedd_transform.models.TransformState

data class TransformDraft(
    val position: String = "",
    val rotation: String = "",
    val pivot: String = ""
)

data class TransformControllerState(
    val applied: TransformState = TransformState(),
    val draft: TransformDraft = TransformDraft()
)

@Stable
class TransformControllerStateHolder internal constructor(
    private val state: MutableState<TransformControllerState>
) {
    val value get() = state.value.applied
    val draftValue get() = state.value.draft

    fun updateApplied(block: (TransformState) -> TransformState) {
        val current = state.value
        state.value = current.copy(applied = block(current.applied))
    }

    fun updateDraft(block: (TransformDraft) -> TransformDraft) {
        val current = state.value
        state.value = current.copy(draft = block(current.draft))
    }
}

@Composable
fun rememberTransformControllerState(initial: TransformControllerState = TransformControllerState()): TransformControllerStateHolder {
    val state = rememberSaveable(saver = TransformControllerStateMutableSaver) { mutableStateOf(initial) }
    return remember { TransformControllerStateHolder(state) }
}

private val TransformControllerStateMutableSaver: Saver<MutableState<TransformControllerState>, Array<String>> =
    Saver(
        save = { state ->
            val s = state.value.applied
            val d = state.value.draft
            arrayOf(
                s.position.x.toString(),
                s.position.y.toString(),
                s.rotation.toString(),
                s.pivot.x.toString(),
                s.pivot.y.toString(),
                d.position,
                d.rotation,
                d.pivot
            )
        },
        restore = { arr ->
            val applied = TransformState(
                position = Offset(arr[0].toFloatOrNull() ?: 0f, arr[1].toFloatOrNull() ?: 0f),
                rotation = arr[2].toFloatOrNull() ?: 0f,
                pivot = Offset(arr[3].toFloatOrNull() ?: 0f, arr[4].toFloatOrNull() ?: 0f)
            )
            val draft = TransformDraft(position = arr[5], rotation = arr[6], pivot = arr[7])
            mutableStateOf(TransformControllerState(applied = applied, draft = draft))
        }
    )
