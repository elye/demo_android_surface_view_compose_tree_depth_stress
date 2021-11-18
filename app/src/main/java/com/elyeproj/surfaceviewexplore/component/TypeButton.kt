package com.elyeproj.surfaceviewexplore.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elyeproj.surfaceviewexplore.LaunchActivity
import com.elyeproj.surfaceviewexplore.Type

@Composable
fun LaunchActivity.TypeButton(type: Type) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { startDrawViewActivity(type) }
    ) {
        Text(text = type.toString())
    }
}
