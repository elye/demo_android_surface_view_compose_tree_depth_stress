package com.elyeproj.surfaceviewexplore

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class ViewSurface @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0)
    : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var job: Job? = null
    private val treeDrawing by lazy {
        TreeDrawingCanvasCustom()
    }

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Do nothing for now
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        job?.cancel()
        job = null
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        job = CoroutineScope(Dispatchers.Default).launch {
            synchronized(holder) {
                val canvas = holder.lockCanvas()
                canvas?.let {
                    it.drawColor(Color.WHITE)
                    val elapsedTime= measureTimeMillis {
                        treeDrawing.draw(it)
                    }
                    Log.d("Measure",
                        "Surface inner took : ${elapsedTime}mS")
                    holder.unlockCanvasAndPost(it)
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(View.resolveSize(desiredWidth, widthMeasureSpec),
                View.resolveSize(desiredHeight, heightMeasureSpec))
    }
}
