package com.elyeproj.surfaceviewexplore

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class TreeDrawingCanvasCustom {

    private val strokePaint = Paint()
        .apply { color = Constant.strokeColor }
        .apply { strokeWidth = Constant.strokeWidth }

    fun draw(canvas: Canvas) {
        val canvasHeight = canvas.height.toFloat()
        val centerX = canvas.width.toFloat() / 2
        val branchAngle = Constant.branchAngle
        val branchLength = Constant.branchLength
        val treeDepth = Constant.treeDepth.toInt()
        val branchAngleDifference = Constant.branchAngleDifference

        drawTree(
            startX = centerX,
            startY = canvasHeight,
            endX = centerX,
            endY = canvasHeight - branchLength,
            depth = treeDepth,
            branchLength = branchLength,
            branchAngle = branchAngle,
            angleDifference = branchAngleDifference,
            canvas = canvas
        )
    }

    private fun drawTree(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        depth: Int,
        branchLength: Float,
        branchAngle: Double,
        angleDifference: Float,
        canvas: Canvas
    ) {
        if (depth == 0) return

        canvas.drawLine(
            startX,
            startY,
            endX,
            endY,
            strokePaint
        )

        // decreasing branch length by (2/3) ratio everytime
        val nextBranchLength = branchLength * 0.67f
        val branchAngleDifference = (PI) * angleDifference

        fun branch(angle: Double, canvas: Canvas) {
            val nextEndOffsetX = endX + (nextBranchLength * cos(angle)).toFloat()
            val nextEndOffsetY = endY + (nextBranchLength * sin(angle)).toFloat()

            drawTree(
                startX = endX,
                startY = endY,
                endX = nextEndOffsetX,
                endY = nextEndOffsetY,
                depth = depth - 1,
                branchLength = nextBranchLength,
                branchAngle = angle,
                angleDifference = angleDifference,
                canvas = canvas
            )
        }
        // Right branch
        branch(angle = branchAngle + branchAngleDifference, canvas)
        // Center branch
        branch(angle = branchAngle, canvas)
        // Left branch
        branch(angle = branchAngle - branchAngleDifference, canvas)
    }
}
