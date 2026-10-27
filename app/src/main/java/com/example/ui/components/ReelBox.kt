package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Symbol

@Composable
fun ReelBox(symbol: Symbol, isSpinning: Boolean, isWinner: Boolean) {
    val scale by animateFloatAsState(if (isWinner) 1.2f else 1.0f, label = "scale")
    val borderColor by animateColorAsState(if (isWinner) Color.Yellow else Color.Gray, label = "border")

    Box(
        modifier = Modifier
            .size(70.dp)
            .scale(scale)
            .background(Color(0xFF222222), RoundedCornerShape(8.dp))
            .border(2.dp, borderColor, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol.icon,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
    }
}
