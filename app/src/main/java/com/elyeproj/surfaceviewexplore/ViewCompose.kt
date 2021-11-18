package com.elyeproj.surfaceviewexplore

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import kotlin.system.measureTimeMillis

class ViewCompose @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    @Composable
    override fun Content() {
        val elapsedTime= measureTimeMillis {
            TreeDrawingCanvasCompose()
        }
        Log.d("Elisha", "Time used $elapsedTime")
    }
}
