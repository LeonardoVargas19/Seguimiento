package com.leoromeo.seguimiento.screen

import android.util.Log
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
    var nombre by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    val isError = remember { mutableStateMapOf<String, Boolean>() }

    fun validateFields(field: String, value: String): Boolean {
        return when (field) {
            "nombre" -> value.length >= 5
            "dec" -> value.length >= 10
            "motivo" -> value.length >= 10
            else -> true

        }
    }



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
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        isError["nombre"] = !validateFields("nombre", it)

                    },

                    placeholder = {
                        if (isError["nombre"] == true) {
                            Text("Debe tener al menos 5 caracteres", color = Color.Red)
                        } else {
                            Text("Nombre del hábito")
                        }


                    },
                    label = { Text("Nombre del hábito") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Agregar hábito"
                        )
                    },
                    isError = isError["nombre"] == true,

                    modifier = Modifier.fillMaxWidth(),

                    )
                Spacer(modifier = Modifier.height(20.dp))
                if (isError["nombre"] == true) {
                    Text(
                        text = "Debe tener al menos 5 caracteres",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                TextField(
                    value = desc,
                    onValueChange = {
                        desc = it
                        isError["dec"] = !validateFields("dec", it)

                    },
                    placeholder = {
                        if (isError["dec"] == true) {
                            Text("Debe tener al menos 10 caracteres", color = Color.Red)
                        } else {
                            Text("Descripción breve")

                        }
                    },
                    isError = isError["dec"] == true,
                    label = { Text("Descripción breve") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Agregar hábito"
                        )
                    },

                    modifier = Modifier.fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(20.dp))
                if (isError["dec"] == true) {
                    Text(
                        text = "Debe tener al menos 10 caracteres",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }


                TextField(
                    value = fecha,
                    onValueChange = {},
                    label = { Text("Activar o desactivar notificaciones") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Agregar hábito"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()

                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = motivo,
                    onValueChange = {
                        motivo = it
                        isError["motivo"] = !validateFields("motivo", it)
                    },
                    label = { Text("Motivación principal.") },
                    placeholder = {
                        if (isError["motivo"] == true) {
                            Text("Debe tener al menos 10 caracteres", color = Color.Red)
                        } else {
                            Text("Motivación principal.")
                        }
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Agregar hábito"
                        )
                    },
                    isError = isError["motivo"] == true,
                    modifier = Modifier.fillMaxWidth(),


                    )
                if (isError["motivo"] == true) {
                    Text(
                        text = "Debe tener al menos 10 caracteres",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }


            }
            FloatingActionButton(
                onClick = {
                    isError["motivo"] = !validateFields("motivo", motivo)
                    isError["nombre"] = !validateFields("nombre", nombre)
                    isError["desc"] = !validateFields("desc", desc)
                    Log.d("TAG", "BodyScreen: $nombre $desc $fecha $motivo")


                },
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