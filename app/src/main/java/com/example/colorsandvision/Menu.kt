package com.example.colorsandvision

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun Fondo(){
    val backgroundImage = painterResource(id = R.drawable.fondo2)
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = backgroundImage,
            contentDescription = "Imagen de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navigationController: NavHostController){
    val navegation = navigationController
    Fondo()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(), // Habilitar padding para la barra de navegación
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val items = listOf(
            Pair(R.drawable.agregar_usuario, "Agregar Paciente"),
            Pair(R.drawable.examen, "Exámen de la vista"),
            Pair(R.drawable.ventas, "Venta"),
            Pair(R.drawable.anteojos, "Catalogo de lentes"),
            Pair(R.drawable.pregunta, "Consulta Paciente"),
            Pair(R.drawable.consultaexamen, "Consulta Exámen"),
            Pair(R.drawable.consultaventa, "Consulta Venta")
        )

        val rows = items.chunked(2) // Divide la lista de items en listas de 2 elementos cada una

        rows.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                rowItems.forEach { item ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp) // Espaciado alrededor de cada tarjeta
                            .size(150.dp), // Hacer las tarjetas cuadradas
                        elevation = CardDefaults.cardElevation(3.dp), // Elevación de la tarjeta
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = CutCornerShape(9.dp),
                        onClick = {
                            when (item.second) {
                                "Venta" -> navegation.navigate("Venta")
                                "Catalogo de lentes" -> navegation.navigate("Catalogo")
                                "Exámen de la vista" -> navegation.navigate("Examen")
                                "Agregar Paciente" -> navegation.navigate("Paciente")
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = item.first),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp) // Ajustar tamaño de la imagen si es necesario
                            )
                            Text(
                                text = item.second,
                                color = colorResource(id = R.color.AzulMarino),
                                fontFamily = FontFamily.Serif,
                                textAlign = TextAlign.Center, // Centrar el texto
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}