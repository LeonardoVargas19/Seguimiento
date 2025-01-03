package com.leoromeo.seguimiento.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Preview
@Composable
fun AddHabitScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF8FAFC))
            .padding(40.dp)
    ) {
        TitleScren()
        Spacer(modifier = Modifier.height(20.dp))
        BodyScreen()
    }

}

@Composable
private fun BodyScreen() {
    Card(
        modifier = Modifier
            .width(400.dp) //Refactorizar
            .height(600.dp),//Refactorzar
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD9EAFD),
            contentColor = Color.Black
        )
    ) {
        Box(
            //TITLE HABITO
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),

            ) {
            Column {
                TextField(
                    value = "",
                    onValueChange = {},

                    placeholder = {Text(text = "Campo obligatorio")},
                    label = { Text("Nombre del hábito") },
                    leadingIcon={Icon(Icons.Default.AccountCircle, contentDescription = "Agregar hábito")},
                    modifier = Modifier.fillMaxWidth(),

                    )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {Text(text = "Campo obligatorio")},
                    label = { Text("Descripción breve") },
                    leadingIcon={Icon(Icons.Default.Add, contentDescription = "Agregar hábito")},
                    modifier = Modifier.fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Activar o desactivar notificaciones") },
                    leadingIcon={Icon(Icons.Default.Add, contentDescription = "Agregar hábito")},
                    modifier = Modifier.fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Motivación principal.") },
                    leadingIcon={Icon(Icons.Default.Favorite, contentDescription = "Agregar hábito")},
                    modifier = Modifier.fillMaxWidth()

                )
                // TODO CREAR UN DATE PIKER PARA SELECCIONAR LA FECHA QUE SE QUIERE HACER

            }
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(50.dp),
                containerColor = Color(0xFF9AA6B2),

                ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar hábito",
                    tint = Color.White
                )
            }
            // TODO CREAR UN MEJOR BOTON PARA AGREGAR EL HABITO


        }

    }
}

@Composable
private fun TitleScren() {
    Card(
        modifier = Modifier
            .width(400.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD9EAFD),
            contentColor = Color.Black
        )
    ) {
        Box( //TITLE HABITO
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Title Habito",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9AA6B2),

                )
        }
    }
}