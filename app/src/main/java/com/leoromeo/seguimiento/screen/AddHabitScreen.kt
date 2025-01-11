package com.leoromeo.seguimiento.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoromeo.seguimiento.domain.validateFields

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BodyScreen() {
    var nombre by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var motivo by remember { mutableStateOf("") }
    val isError = remember { mutableStateMapOf<String, Boolean>() }
    val state = rememberDatePickerState()
    var showDialog by remember { mutableStateOf(false) }




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

                formTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    isError = isError,
                    typeError = "nombre",
                    label = "Nombre del hábito",
                    imageVector = Icons.Default.AccountCircle
                )
                Spacer(modifier = Modifier.height(20.dp))
                if (isError["nombre"] == true) {
                    Text(
                        text = "Debe tener al menos 5 caracteres",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                formTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    isError = isError,
                    typeError = "desc",
                    label = "Descripción breve",
                    imageVector = Icons.Default.Add
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
//                  TODO AGREGAR LA FECHA REFACTORIACION
                )



                Spacer(modifier = Modifier.height(20.dp))
                formTextField(
                    value = motivo,
                    onValueChange = { motivo = it },
                    isError = isError,
                    typeError = "motivo",
                    label = "Motivación principal",
                    imageVector = Icons.Default.Favorite
                )

                if (isError["motivo"] == true) {
                    Text(
                        text = "Debe tener al menos 10 caracteres",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }
                Button(
                    onClick = {
                        showDialog = true
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Mostrar PickerDate")
                }
                if (showDialog) {
                    DatePickerDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    // Obtén la fecha seleccionada y actualiza la variable
                                    val selectedDate = state.selectedDateMillis
                                    if (selectedDate != null) {
                                        val formattedDate = java.text.SimpleDateFormat(
                                            "dd/MM/yyyy",
                                            java.util.Locale.getDefault()
                                        )
                                            .format(java.util.Date(selectedDate))
                                        fecha = formattedDate
                                    }
                                    showDialog = false
                                }
                            ) {
                                Text("Confirmar")
                            }
                        }
                    ) {
                        DatePicker(state = state)
                    }
                }


            }
            FloatingActionButton(
                onClick = {
                    isError["motivo"] = !validateFields("motivo", motivo)
                    isError["nombre"] = !validateFields("nombre", nombre)
                    isError["desc"] = !validateFields("desc", desc)
                    Log.d(
                        "TAG",
                        "BodyScreen: Nombre: $nombre, Descripción: $desc, Fecha: $fecha, Motivación: $motivo"
                    )
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(50.dp),
                containerColor = Color(0xFF9AA6B2)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar hábito",
                    tint = Color.White
                )
            }
        }

    }
}

@Composable
private fun formTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: SnapshotStateMap<String, Boolean>,
    typeError: String,
    label: String,
    imageVector: ImageVector
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it) // Actualiza el estado en el componente padre
            isError[typeError] = !validateFields(typeError, it) // Valida el campo
        },
        label = { Text(label) },
        placeholder = {
            if (isError[typeError] == true) {
                Text("Debe tener al menos 10 caracteres", color = Color.Red)
            } else {
                Text(label)
            }
        },
        leadingIcon = {
            Icon(imageVector, contentDescription = "Agregar hábito")
        },
        isError = isError[typeError] == true,
        modifier = Modifier.fillMaxWidth()
    )
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


