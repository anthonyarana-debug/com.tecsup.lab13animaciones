package com.tecsup.lab13animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Ejercicio2Screen() {
    var esAzul by remember { mutableStateOf(true) }

    val colorAnimado by animateColorAsState(
        targetValue = if (esAzul) Color.Blue else Color.Green,
        animationSpec = tween(durationMillis = 800),
        label = "colorAnimado"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(180.dp)
                .background(colorAnimado)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { esAzul = !esAzul }) {
            Text(
                text = if (esAzul) "Cambiar a Verde" else "Cambiar a Azul",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Usando: tween(800ms)",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}