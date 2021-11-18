package com.elyeproj.surfaceviewexplore

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TreeDrawingCanvasCompose() {
    Column(
        modifier = Modifier.defaultMinSize(minHeight = 700.dp)
    ) {
        TreeDrawingCanvasCompose(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            branchColor = MaterialTheme.colors.onBackground,
            branchLength = Constant.branchLength,
            treeDepth = Constant.treeDepth,
            branchAngleDifference = Constant.branchAngleDifference
        )
    }
}

@Composable
fun TreeDrawingCanvasCompose(
    modifier: Modifier,
    branchColor: Color,
    branchLength: Float,
    treeDepth: Int,
    branchAngleDifference: Float
) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        val canvasHeight = size.height
        val center = size.center

        val branchAngle = Constant.branchAngle

        drawTree(
            start = Offset(center.x, canvasHeight),
            end = Offset(x = center.x, y = canvasHeight - branchLength),
            depth = treeDepth,
            branchLength = branchLength,
            branchAngle = branchAngle,
            angleDifference = branchAngleDifference,
            branchColor = branchColor
        )
    }
}

fun DrawScope.drawTree(
    start: Offset,
    end: Offset,
    depth: Int,
    branchLength: Float,
    branchAngle: Double,
    angleDifference: Float,
    branchColor: Color
) {
    if (depth == 0) return

    drawLine(
        start = start,
        end = end,
        color = branchColor,
        strokeWidth = Constant.strokeWidth
    )

    // decreasing branch length by (2/3) ratio everytime
    val nextBranchLength = branchLength * 0.67f
    val branchAngleDifference = (PI) * angleDifference

    fun branch(angle: Double) {
        val nextEndOffset = Offset(
            x = (end.x + (nextBranchLength * cos(angle))).toFloat(),
            y = (end.y + (nextBranchLength * sin(angle))).toFloat()
        )

        drawTree(
            start = end,
            end = nextEndOffset,
            depth = depth - 1,
            branchLength = nextBranchLength,
            branchAngle = angle,
            angleDifference = angleDifference,
            branchColor = branchColor
        )
    }

    // Right branch
    branch(angle = branchAngle + branchAngleDifference)
    // Center branch
    branch(angle = branchAngle)
    // Left branch
    branch(angle = branchAngle - branchAngleDifference)
}
