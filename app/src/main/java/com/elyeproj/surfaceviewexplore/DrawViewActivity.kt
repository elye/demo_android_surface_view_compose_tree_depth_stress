package com.elyeproj.surfaceviewexplore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

enum class Type {
    SURFACE, COMPOSE, CUSTOM
}

class DrawViewActivity : AppCompatActivity() {

    companion object {
        const val KEY = "Key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.get(KEY).apply {
            when(this) {
                Type.COMPOSE -> setContentView(R.layout.activity_composeview)
                Type.SURFACE -> setContentView(R.layout.activity_surfaceview)
                Type.CUSTOM -> setContentView(R.layout.activity_customview)
            }
            title = this.toString()
        }
    }
}
