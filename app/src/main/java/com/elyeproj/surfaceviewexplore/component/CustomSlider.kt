package com.elyeproj.surfaceviewexplore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomSlider(
    sliderText: String,
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>
) {
    var changeValue by remember { mutableStateOf(sliderValue) }

    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "$sliderText ${changeValue.toInt()}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        Slider(
            value = sliderValue,
            onValueChange = { value ->
                onSliderValueChange.invoke(value)
                changeValue = value
            },
            valueRange = valueRange,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.onBackground,
                inactiveTrackColor = Color.Gray,
                activeTrackColor = Color.Black
            ),
        )
    }
}