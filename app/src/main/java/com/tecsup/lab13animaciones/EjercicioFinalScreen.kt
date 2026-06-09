package com.tecsup.lab13animaciones

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EjercicioFinalScreen() {
    var jugando by remember { mutableStateOf(false) }
    var puntos by remember { mutableStateOf(0) }
    var naveVisible by remember { mutableStateOf(true) }

    // Animación de posición de la nave
    val naveX by animateDpAsState(
        targetValue = if (jugando) 200.dp else 0.dp,
        animationSpec = tween(1000),
        label = "naveX"
    )
    val naveY by animateDpAsState(
        targetValue = if (jugando) (-100).dp else 0.dp,
        animationSpec = tween(1000),
        label = "naveY"
    )

    // Animación de tamaño de la nave
    val naveTamano by animateDpAsState(
        targetValue = if (jugando) 80.dp else 50.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "naveTamano"
    )

    // Color del fondo animado
    val fondoColor by animateColorAsState(
        targetValue = if (jugando) Color(0xFF0D1B2A) else Color(0xFF1A237E),
        animationSpec = tween(800),
        label = "fondo"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoColor),
        contentAlignment = Alignment.Center
    ) {
        // Estrellas decorativas
        repeat(5) { i ->
            Box(
                modifier = Modifier
                    .offset(x = (i * 60 - 120).dp, y = (i * 80 - 200).dp)
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.6f))
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Puntaje
            AnimatedContent(
                targetState = puntos,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "puntos"
            ) { p ->
                Text(
                    text = "⭐ Puntos: $p",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Yellow
                )
            }

            // Nave espacial
            AnimatedVisibility(
                visible = naveVisible,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Box(
                    modifier = Modifier
                        .offset(x = naveX, y = naveY)
                        .size(naveTamano)
                        .clip(CircleShape)
                        .background(Color.Cyan),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🚀", fontSize = 28.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        jugando = !jugando
                        if (jugando) puntos += 10
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (jugando) Color.Red else Color.Green
                    )
                ) {
                    Text(
                        text = if (jugando) "⏹ Detener" else "▶ Lanzar",
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        naveVisible = !naveVisible
                        if (!naveVisible) puntos -= 5
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00))
                ) {
                    Text("💥 Impacto", color = Color.White)
                }
            }

            Button(
                onClick = {
                    puntos = 0
                    jugando = false
                    naveVisible = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("🔄 Reiniciar", color = Color.White)
            }
        }
    }
}