package com.elyeproj.surfaceviewexplore

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elyeproj.surfaceviewexplore.Constant.treeDepth
import com.elyeproj.surfaceviewexplore.component.TypeButton

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var treeDepth by remember { mutableStateOf(treeDepth) }
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TypeButton(Type.COMPOSE)
                TypeButton(Type.CUSTOM)
                TypeButton(Type.SURFACE)
                CustomSlider(
                    sliderText = "Tree Depth",
                    sliderValue = treeDepth,
                    onSliderValueChange = {
                        Constant.treeDepth = it
                        treeDepth = it
                    },
                    valueRange = 2f..15f
                )
            }
        }
    }

    fun startDrawViewActivity(type: Type) {
        startActivity(Intent(this, DrawViewActivity::class.java).apply {
            putExtra(DrawViewActivity.KEY, type)
        })
    }
}
