package com.elyeproj.surfaceviewexplore

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class ViewCustom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val treeDrawing by lazy {
        TreeDrawingCanvasCustom()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        treeDrawing.draw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec))
    }
}
