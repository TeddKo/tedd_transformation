package com.tedd.tedd_transform.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tedd.tedd_transform.R
import com.tedd.tedd_transform.constants.DrawingConstants
import com.tedd.tedd_transform.models.Vertex

@Composable
fun VertexCoordinatesDisplay(
    modifier: Modifier = Modifier,
    vertices: List<Vertex>
) {
    Column(
        modifier = modifier
            .border(2.dp, Color.Gray, RoundedCornerShape(DrawingConstants.CORNER_RADIUS))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.vertex_coordinates_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        vertices.forEach { vertex ->
            VertexRow(vertex)
        }
    }
}

@Composable
private fun VertexRow(vertex: Vertex) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(vertex.type.stringResId),
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "%.2f, %.2f".format(vertex.x, vertex.y),
            fontFamily = FontFamily.Monospace
        )
    }
}