package com.elyeproj.surfaceviewexplore

import android.graphics.Color
import kotlin.math.PI
import androidx.compose.ui.graphics.Color as ComposeColor


object Constant {
    const val strokeColor = Color.BLACK
    const val strokeWidth = 3f
    const val branchLength = 350f
    var treeDepth = 12f
    const val branchAngleDifference = 0.25f
    const val branchAngle = -(PI / 2)
}