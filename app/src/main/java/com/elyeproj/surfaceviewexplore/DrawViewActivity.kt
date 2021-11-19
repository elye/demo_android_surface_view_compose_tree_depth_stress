package com.elyeproj.surfaceviewexplore

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity


enum class Type {
    SURFACE, COMPOSE, CUSTOM
}

class DrawViewActivity : AppCompatActivity() {

    companion object {
        const val KEY = "Key"
    }

    private var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.get(KEY).apply {
            when (this) {
                Type.COMPOSE -> setContentView(R.layout.activity_composeview)
                Type.SURFACE -> setContentView(R.layout.activity_surfaceview)
                Type.CUSTOM -> setContentView(R.layout.activity_customview)
            }
            title = this.toString()

            val view: View = findViewById(R.id.test_view)
            view.viewTreeObserver.addOnGlobalLayoutListener {
                startTime = if (startTime == 0L) {
                    System.nanoTime()
                } else {
                    Log.d("Measure",
                        "$title took : ${((System.nanoTime()-startTime)/1000000)}mS")
                    0L
                }
            }
        }


    }
}
