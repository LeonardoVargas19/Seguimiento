package com.leoromeo.seguimiento.screen

import android.graphics.fonts.FontStyle
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment


@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun HabitListScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(9.dp), // Espaciado entre tarjetas
        contentPadding = PaddingValues(16.dp)
    )
    {
        items(19) { habit ->
            CardHabit()

        }
    }
}

@Composable
private fun CardHabit() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .width(400.dp)
                .height(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF6F4F0),
                contentColor = Color.Black
            )


        ) {
            Column() {
                var progress: Float by remember { mutableFloatStateOf(0.2f) }
                val animationDuration by animateFloatAsState(
                    targetValue = progress,
                    animationSpec = tween(durationMillis = 1000)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = "Title Habito",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4DA1A9),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = " Frecuencia: 1 semana",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }


                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Title Descripcion",
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 20.sp, // Tamaño de letra
                    fontWeight = FontWeight.Medium, // Negrita
                    color = Color.Black // Color del texto
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(

                ) {
                    LinearProgressIndicator(

                        progress = { animationDuration },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        color = _getColorForProgress(progress),
                        trackColor = Color(0xffd7dbdd)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = when {
                            progress < 0.5f -> Icons.Filled.Warning
                            progress in 0.5f..0.8f -> Icons.Filled.Check
                            else -> Icons.Filled.Star
                        },
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }


        }
    }
}


@Composable
fun _getColorForProgress(progress: Float): Color {
    return when {
        progress < 0.5f -> Color(0XFFF1948a) // Menor al 50%
        progress in 0.5f..0.8f -> Color(0Xfff9e79f) // Entre 50% y 80%
        progress > 0.8f -> Color(0Xff7dcea0) // Mayor al 80%
        else -> Color.Gray // Color por defecto si ocurre algo inesperado
    }
}
