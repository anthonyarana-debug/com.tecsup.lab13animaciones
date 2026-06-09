package com.tecsup.lab13animaciones

import androidx.compose.animation.core.animateDpAsState
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
fun Ejercicio3Screen() {
    var agrandado by remember { mutableStateOf(false) }

    val tamano by animateDpAsState(
        targetValue = if (agrandado) 200.dp else 100.dp,
        animationSpec = tween(durationMillis = 600),
        label = "tamano"
    )

    val offsetX by animateDpAsState(
        targetValue = if (agrandado) 80.dp else 0.dp,
        animationSpec = tween(durationMillis = 600),
        label = "offsetX"
    )

    val offsetY by animateDpAsState(
        targetValue = if (agrandado) 100.dp else 0.dp,
        animationSpec = tween(durationMillis = 600),
        label = "offsetY"
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
                .offset(x = offsetX, y = offsetY)
                .size(tamano)
                .background(Color.Red)
        )

        Spacer(modifier = Modifier.height(140.dp))

        Button(onClick = { agrandado = !agrandado }) {
            Text(
                text = if (agrandado) "Reducir y Regresar" else "Agrandar y Mover",
                fontSize = 16.sp
            )
        }
    }
}