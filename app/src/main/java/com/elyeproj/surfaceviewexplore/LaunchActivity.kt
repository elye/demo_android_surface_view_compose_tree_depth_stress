package com.elyeproj.surfaceviewexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elyeproj.surfaceviewexplore.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCompose.setOnClickListener {
            startDrawViewActivity(Type.COMPOSE)
        }

        binding.btnCustom.setOnClickListener {
            startDrawViewActivity(Type.CUSTOM)
        }

        binding.btnSurface.setOnClickListener {
            startDrawViewActivity(Type.SURFACE)
        }
    }

    private fun startDrawViewActivity(type: Type) {
        startActivity(Intent(this, DrawViewActivity::class.java).apply {
            putExtra(DrawViewActivity.KEY, type)
        })
    }
}
